import { AbstractControl, FormGroup, ValidatorFn } from '@angular/forms';

export function noSpacesPwValidator(): ValidatorFn {
  return (control: AbstractControl) => {
    if (!control.value) {
      return null;
    }
    const noSpaces = !control.value.includes(' ');
    return noSpaces ? null : { spaceInPassword: true };
  };
}

export function hasNumberPwValidator(): ValidatorFn {
  return (control: AbstractControl) => {
    const hasNumber = /[0-9]/.test(control.value);
    return hasNumber ? null : { noNumberInPw: true };
  };
}

export function hasLowercasePwValidator(): ValidatorFn {
  return (control: AbstractControl) => {
    const hasLowercase = /[a-z]/.test(control.value);
    return hasLowercase ? null : { noLowercaseInPw: true };
  };
}

export function hasUppercasePwValidator(): ValidatorFn {
  return (control: AbstractControl) => {
    const hasUppercase = /[A-Z]/.test(control.value);
    return hasUppercase ? null : { noUppercaseInPw: true };
  };
}

export function confirmPasswordValidator(form: FormGroup): ValidatorFn {
  return (control: AbstractControl) => {
    return form.get('password')?.value === control.value &&
      control.value &&
      form.get('password')?.valid
      ? null
      : { passwordNotConfirmed: true };
  };
}

export function confirmNewPasswordValidator(form: FormGroup): ValidatorFn {
  return (control: AbstractControl) => {
    return form.get('newPassword')?.value === control.value &&
      control.value &&
      form.get('newPassword')?.valid
      ? null
      : { newPasswordNotConfirmed: true };
  };
}
