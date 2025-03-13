import { Component, inject } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-boardspage',
  imports: [],
  templateUrl: './boardspage.component.html',
  styleUrl: './boardspage.component.css',
})
export class BoardspageComponent {
  loginService = inject(LoginService);
  router = inject(Router);

  ngOnInit(): void {
    if (!this.loginService.getLoggedUser()) {
      this.router.navigate(['/login']);
    }
  }
}
