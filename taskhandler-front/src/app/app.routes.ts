import { Routes } from '@angular/router';
import { LoginpageComponent } from './pages/loginpage/loginpage.component';
import { RegisterpageComponent } from './pages/registerpage/registerpage.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginpageComponent,
  },
  {
    path: 'signin',
    component: RegisterpageComponent,
  },
];
