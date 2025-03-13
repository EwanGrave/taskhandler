import { Component, inject } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UserControllerService, UserDTOWithPassword } from '../../../../api';
import { shaEncrypt } from '../../utils/StringUtils';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registerpage',
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './registerpage.component.html',
  styleUrl: './registerpage.component.css',
})
export class RegisterpageComponent {
  isError: boolean = false;
  userService = inject(UserControllerService);
  route = inject(Router);

  registerForm = new FormGroup({
    username: new FormControl<string>('', [Validators.required]),
    password: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
    ]),
    confirmPassword: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
    ]),
  });

  register(): void {
    if (
      this.registerForm.valid &&
      this.registerForm.value.password ===
        this.registerForm.value.confirmPassword
    ) {
      const user: UserDTOWithPassword = {
        username: this.registerForm.value.username ?? '',
        password: shaEncrypt(this.registerForm.value.password ?? '', 'sha256'),
      };
      this.userService.createUser(user).subscribe();
      this.route.navigate(['/login']);
    }
  }
}
