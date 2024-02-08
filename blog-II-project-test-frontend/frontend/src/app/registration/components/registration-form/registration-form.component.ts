import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import {
  faIdCard,
  faEnvelope,
  faCircleCheck,
  faCircleXmark,
} from '@fortawesome/free-regular-svg-icons';
import { faKey, faUserSecret } from '@fortawesome/free-solid-svg-icons';
import { UserModel } from '../../models/user.model';
import { RegistrationService } from '../../services/registration.service';
import { Router } from '@angular/router';
import { InputError } from '../../models/inputError.model';
import {
  confirmPasswordValidator,
  hasLowercasePwValidator,
  hasNumberPwValidator,
  hasUppercasePwValidator,
  noSpacesPwValidator,
} from 'src/app/_validators';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css'],
})
export class RegistrationFormComponent implements OnInit {
  constructor(
    private registrationService: RegistrationService,
    private router: Router
  ) {}

  registrationForm!: FormGroup;
  formSent: boolean = false;

  isPwFocused: boolean = false;

  backendError: InputError[] = [];

  faIdCard = faIdCard;
  faEnvelope = faEnvelope;
  faUserSecret = faUserSecret;
  faKey = faKey;
  faCircleCheck = faCircleCheck;
  faCircleXmark = faCircleXmark;

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      username: new FormControl('', [
        Validators.minLength(3),
        Validators.required,
      ]),
      //a Validators.email nem felel meg a backendes validációnak, újat kell írni
      email: new FormControl('', [Validators.email, Validators.required]),
      displayName: new FormControl('', [
        Validators.minLength(3),
        Validators.required,
      ]),
      password: new FormControl('', [
        noSpacesPwValidator(),
        hasNumberPwValidator(),
        hasLowercasePwValidator(),
        hasUppercasePwValidator(),
        Validators.minLength(5),
        Validators.required,
      ]),
      termsCheckbox: new FormControl(null, [Validators.requiredTrue]),
    });

    this.registrationForm.addControl(
      'confirmPassword',
      new FormControl('', [
        confirmPasswordValidator(this.registrationForm),
        Validators.required,
      ])
    );

    this.registrationForm.get('password')!.valueChanges.subscribe(() => {
      this.registrationForm.get('confirmPassword')!.updateValueAndValidity();
      if (!this.registrationForm.get('password')!.value) {
        this.registrationForm.get('confirmPassword')!.setValue('');
      }
    });
  }

  setInputToUntouched(input: AbstractControl | null) {
    if (input?.touched) {
      input?.markAsUntouched();
    }
  }

  onPwFocus() {
    this.isPwFocused = true;
  }

  onPwBlur() {
    this.isPwFocused = false;
  }

  getUsernameServerError() {
    return this.backendError.find((error) => {
      return error.field === 'username';
    })?.errorMessage;
  }

  getDisplayNameServerError() {
    return this.backendError.find((error) => {
      return error.field === 'displayName';
    })?.errorMessage;
  }

  getEmailServerError() {
    return this.backendError.find((error) => {
      return error.field === 'email';
    })?.errorMessage;
  }

  getPwServerError() {
    return this.backendError.find((error) => {
      return error.field === 'password';
    })?.errorMessage;
  }

  submitRegistration() {
    if (this.registrationForm.invalid || this.formSent) return;
    this.formSent = true;

    if (this.registrationForm.valid) {
      const newUser: UserModel = {
        username: this.registrationForm.get('username')?.value,
        email: this.registrationForm.get('email')?.value,
        displayName: this.registrationForm.get('displayName')?.value,
        password: this.registrationForm.get('password')?.value,
      };

      this.registrationService.saveUser(newUser).subscribe({
        next: () => {
          this.router.navigate(['/registration/thank-you']);
        },
        error: (err) => {
          this.backendError = err.error;
        },
        complete: () => {
          this.formSent = false;
        },
      });
    }
  }

  get username() {
    return this.registrationForm.get('username');
  }

  get email() {
    return this.registrationForm.get('email');
  }

  get displayName() {
    return this.registrationForm.get('displayName');
  }

  get password() {
    return this.registrationForm.get('password');
  }

  get confirmPassword() {
    return this.registrationForm.get('confirmPassword');
  }

  get termsCheckbox() {
    return this.registrationForm.get('termsCheckbox');
  }
}
