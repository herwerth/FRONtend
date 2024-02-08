import { ToastrService } from 'ngx-toastr';
import { ChangeUserDataService } from './../../services/change-user-data.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { InputError } from 'src/app/registration/models/inputError.model';
import { UserData } from 'src/app/sign-in/models/userData.model';
import { AuthenticationService } from 'src/app/sign-in/services/authentication.service';

@Component({
  selector: 'app-user-data-form',
  templateUrl: './user-data-form.component.html',
  styleUrls: ['./user-data-form.component.css'],
})
export class UserDataFormComponent implements OnInit {
  user!: UserData;
  userDataForm!: FormGroup;
  showSubmitBtn: boolean = false;
  backendErrors!: InputError[];

  formSent: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private changeUserDataService: ChangeUserDataService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.authService.getUser().subscribe((data) => {
      if (data === null) {
        return;
      }
      this.user = data;

      this.userDataForm = new FormGroup({
        username: new FormControl(
          { value: this.user.username, disabled: true },
          [Validators.minLength(3)]
        ),
        email: new FormControl({ value: this.user.email, disabled: true }, [
          Validators.email,
        ]),
        displayName: new FormControl(
          { value: this.user.displayName, disabled: true },
          [Validators.minLength(3)]
        ),
      });
    });
  }

  editUserData(): void {
    this.username?.enable();
    this.email?.enable();
    this.displayName?.enable();

    this.showSubmitBtn = true;
  }

  disableForm(): void {
    this.username?.disable();
    this.email?.disable();
    this.displayName?.disable();

    this.showSubmitBtn = false;
  }

  submitUserData() {
    if (this.formSent) return;
    this.formSent = true;

    const requestData = this.userDataForm.value;
    for (const key in requestData) {
      if (requestData.hasOwnProperty(key) && this.user.hasOwnProperty(key)) {
        if (requestData[key] === (this.user as any)[key]) {
          requestData[key] = '';
        }
      }
    }

    this.changeUserDataService.saveUserData(requestData).subscribe({
      next: () => {
        this.toastr.success('', 'User data successfully updated', {
          positionClass: 'toast-bottom-center',
        });
      },
      error: (err) => {
        this.backendErrors = err.error;
      },
      complete: () => {
        this.formSent = false;
        this.disableForm();
      },
    });
  }

  getUserRole() {
    return this.user?.role[0] === 'ROLE_ADMIN' ? 'Admin user' : 'Regular user';
  }

  get username() {
    return this.userDataForm.get('username');
  }

  get email() {
    return this.userDataForm.get('email');
  }

  get displayName() {
    return this.userDataForm.get('displayName');
  }
}
