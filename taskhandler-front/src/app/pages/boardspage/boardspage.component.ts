import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { HeaderComponent } from '../../components/header/header/header.component';
import { BoardControllerService, BoardDTO, UserDTO } from '../../../../api';

@Component({
  selector: 'app-boardspage',
  imports: [HeaderComponent],
  templateUrl: './boardspage.component.html',
  styleUrl: './boardspage.component.css',
})
export class WorkspacespageComponent {
  loginService = inject(LoginService);
  boardsService = inject(BoardControllerService);
  boards: BoardDTO[] = [];
  user!: UserDTO | null;
  router = inject(Router);

  ngOnInit(): void {
    this.user = this.loginService.getLoggedUser();
    if (!this.user || !this.user.idUser) {
      this.router.navigate(['/login']);
    } else {
      this.boardsService
        .getBoardByUser(this.user.idUser)
        .subscribe((boards) => (this.boards = boards));
    }
  }
}
