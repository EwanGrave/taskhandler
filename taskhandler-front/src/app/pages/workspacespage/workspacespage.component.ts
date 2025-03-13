import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { WorkspaceControllerService, WorkspaceDTO } from '../../../../api';
import { HeaderComponent } from '../../components/header/header/header.component';

@Component({
  selector: 'app-workspacespage',
  imports: [HeaderComponent],
  templateUrl: './workspacespage.component.html',
  styleUrl: './workspacespage.component.css',
})
export class WorkspacespageComponent {
  loginService = inject(LoginService);
  workspacesService = inject(WorkspaceControllerService);
  workspaces: WorkspaceDTO[] = [];
  router = inject(Router);

  ngOnInit(): void {
    const user = this.loginService.getLoggedUser();
    if (!user || !user.idUser) {
      this.router.navigate(['/login']);
    } else {
      this.workspacesService
        .getWorkspaceByUser(user.idUser)
        .subscribe((workspaces) => (this.workspaces = workspaces));
    }
  }
}
