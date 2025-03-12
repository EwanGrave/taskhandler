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
import { LoginService } from '../../services/login.service';
import { shaEncrypt } from '../../utils/StringUtils';

@Component({
  selector: 'app-loginpage',
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.css',
})
export class LoginpageComponent {
  loginService = inject(LoginService);
  isError: boolean = false;

  loginForm = new FormGroup({
    username: new FormControl<string>('', [Validators.required]),
    password: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
    ]),
  });

  login(): void {
    if (this.loginForm.valid) {
      this.loginService
        .login({
          username: this.loginForm.value.username ?? '',
          password: shaEncrypt(this.loginForm.value.password ?? '', 'sha256'),
        })
        .then((res) => (this.isError = !res));
    }
  }
}
