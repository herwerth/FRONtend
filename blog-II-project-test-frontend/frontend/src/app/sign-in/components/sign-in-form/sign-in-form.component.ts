import { Router } from '@angular/router';
import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { faImagePortrait, faKey } from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-sign-in-form',
  templateUrl: './sign-in-form.component.html',
  styleUrls: ['./sign-in-form.component.css'],
})
export class SignInFormComponent implements OnInit {
  signInForm!: FormGroup;
  formSent: boolean = false;

  faImagePortrait = faImagePortrait;
  faKey = faKey;

  serverReplyError: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.signInForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(5),
      ]),
    });
  }

  submitLoginForm(): void {
    if (this.formSent) return;
    this.formSent = true;
    const loginData = this.signInForm.value;
    this.authService.login(loginData).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.formSent = false;
        this.serverReplyError = true;
        this.toastr.error('Please try again.', 'Invalid username or password', {
          positionClass: 'toast-bottom-center',
        });
      },
      complete: () => {
        this.formSent = false;
      },
    });
  }

  setInputToUntouched(input: AbstractControl | null) {
    if (input?.touched) {
      input?.markAsUntouched();
    }
  }

  get username() {
    return this.signInForm.get('username');
  }

  get password() {
    return this.signInForm.get('password');
  }
}
