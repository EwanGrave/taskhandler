import { Component, inject, Input } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { UserDTO } from '../../../../../api';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-header',
  imports: [MatButtonModule, MatMenuModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  @Input({ required: true }) user!: UserDTO | null;
  loginService = inject(LoginService);

  disconnect(): void {
    this.loginService.logout();
  }
}
