import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-workspacespage',
  imports: [],
  templateUrl: './workspacespage.component.html',
  styleUrl: './workspacespage.component.css',
})
export class WorkspacespageComponent {
  loginService = inject(LoginService);
  router = inject(Router);

  ngOnInit(): void {
    if (!this.loginService.getLoggedUser()) {
      this.router.navigate(['/login']);
    }
  }
}
