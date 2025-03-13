import { inject, Injectable } from '@angular/core';
import {
  UserControllerService,
  UserDTO,
  UserDTOWithPassword,
} from '../../../api';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private userLogin = inject(UserControllerService);
  private router = inject(Router);
  private readonly USER_DATA_KEY = 'USER_DATA';

  constructor() {}

  private setStorageItem(key: string, value: string): void {
    localStorage.setItem(key, value);
    sessionStorage.setItem(key, value);
  }

  private removeStorageItem(key: string): void {
    localStorage.removeItem(key);
    sessionStorage.removeItem(key);
  }

  getLoggedUser(): UserDTO | null {
    const user = localStorage.getItem(this.USER_DATA_KEY);
    return user ? JSON.parse(user) : null;
  }

  isLoggedIn(): boolean {
    return !!sessionStorage.getItem(this.USER_DATA_KEY);
  }

  logout(): void {
    this.removeStorageItem(this.USER_DATA_KEY);
    sessionStorage.clear();
    window.location.reload();
  }

  login(user: UserDTOWithPassword): Promise<boolean> {
    return new Promise<boolean>((resolve) => {
      this.userLogin.logUser(user).subscribe({
        next: (value) => {
          if (value) {
            this.setStorageItem(this.USER_DATA_KEY, JSON.stringify(value));
            this.router.navigate(['u', user.username, 'workspaces']);
          }
          resolve(value !== null);
        },
      });
    });
  }
}
