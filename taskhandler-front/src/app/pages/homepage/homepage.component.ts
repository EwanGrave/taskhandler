import { Component, inject } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  imports: [],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css',
})
export class HomepageComponent {
  loginService = inject(LoginService);
  router = inject(Router);

  ngOnInit(): void {
    if (!this.loginService.getLoggedUser()) {
      this.router.navigate(['/login']);
    }
  }
}
