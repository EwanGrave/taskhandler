import { Routes } from '@angular/router';
import { LoginpageComponent } from './pages/loginpage/loginpage.component';
import { RegisterpageComponent } from './pages/registerpage/registerpage.component';
import { WorkspacespageComponent } from './pages/boardspage/boardspage.component';

export const routes: Routes = [
  {
    path: '',
    component: LoginpageComponent,
  },
  {
    path: 'u/boards',
    component: WorkspacespageComponent,
  },
  {
    path: 'login',
    component: LoginpageComponent,
  },
  {
    path: 'signin',
    component: RegisterpageComponent,
  },
];
