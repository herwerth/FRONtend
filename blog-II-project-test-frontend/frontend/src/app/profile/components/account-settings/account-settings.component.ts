import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {
  faCircleCheck,
  faCircleXmark,
} from '@fortawesome/free-regular-svg-icons';
import {
  confirmNewPasswordValidator,
  hasLowercasePwValidator,
  hasNumberPwValidator,
  hasUppercasePwValidator,
  noSpacesPwValidator,
} from 'src/app/_validators';
import { UserData } from 'src/app/sign-in/models/userData.model';
import { AuthenticationService } from 'src/app/sign-in/services/authentication.service';
import { ChangeUserDataService } from '../../services/change-user-data.service';
import { InputError } from 'src/app/registration/models/inputError.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css'],
})
export class AccountSettingsComponent implements OnInit {
  constructor(
    private authService: AuthenticationService,
    private changeUserDataService: ChangeUserDataService,
    private toastr: ToastrService
  ) {}

  user!: UserData;
  changePwForm: FormGroup = new FormGroup({
    oldPassword: new FormControl('', [
      Validators.minLength(5),
      Validators.required,
    ]),
    newPassword: new FormControl({ value: '', disabled: true }, [
      noSpacesPwValidator(),
      hasNumberPwValidator(),
      hasLowercasePwValidator(),
      hasUppercasePwValidator(),
      Validators.minLength(5),
      Validators.required,
    ]),
  });

  isNewPwFocused: boolean = false;
  formSent: boolean = false;

  backendError: InputError[] = [];
  backendReply: string | null = '';

  faCircleCheck = faCircleCheck;
  faCircleXmark = faCircleXmark;

  ngOnInit(): void {
    this.changePwForm.addControl(
      'newPasswordAgain',
      new FormControl({ value: '', disabled: true }, [
        confirmNewPasswordValidator(this.changePwForm),
        Validators.required,
      ])
    );

    this.oldPassword?.valueChanges.subscribe(() => {
      if (this.oldPassword?.valid && this.oldPassword.value) {
        this.newPassword?.enable();
      } else {
        this.newPassword?.setValue('');
        this.newPassword?.disable();
      }
    });

    this.newPassword?.valueChanges.subscribe(() => {
      if (this.newPassword?.valid && this.newPassword.value) {
        this.newPasswordAgain?.enable();
      } else {
        this.newPasswordAgain?.setValue('');
        this.newPasswordAgain?.disable();
      }
      this.newPasswordAgain?.updateValueAndValidity();
      if (!this.newPassword?.value) {
        this.newPasswordAgain?.setValue('');
      }
    });

    this.authService.getUser().subscribe((data) => {
      if (data === null) {
        return;
      }
      this.user = data;
    });
  }

  resetForm() {
    this.oldPassword?.reset();
    this.newPassword?.reset();
    this.newPasswordAgain?.reset();

    this.newPassword?.disable();
    this.newPasswordAgain?.disable();
  }

  getOldPasswordError() {
    return this.backendError.find((error) => {
      return error.field === 'oldPassword';
    })?.errorMessage;
  }

  getNewPasswordError() {
    return this.backendError.find((error) => {
      return error.field === 'newPassword';
    })?.errorMessage;
  }

  getNewPasswordAgainError() {
    return this.backendError.find((error) => {
      return error.field === 'newPasswordAgain';
    })?.errorMessage;
  }

  submitPwChange() {
    if (this.formSent) return;
    this.formSent = true;

    this.changeUserDataService
      .changePassword(this.changePwForm.value)
      .subscribe({
        next: (reply) => {
          this.toastr.success('', reply, {
            positionClass: 'toast-bottom-center',
          });
        },
        error: (err) => {
          console.log('Error:', err);
          this.backendError = err.error;
          this.formSent = false;
          this.resetForm();
        },
        complete: () => {
          this.formSent = false;
          this.resetForm();
        },
      });
  }

  get oldPassword() {
    return this.changePwForm.get('oldPassword');
  }

  get newPassword() {
    return this.changePwForm.get('newPassword');
  }

  get newPasswordAgain() {
    return this.changePwForm.get('newPasswordAgain');
  }
}
