import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { BoardControllerService, BoardDTO, UserDTO } from '../../../../api';
import { CreateboardComponent } from '../../components/dialog/createboard/createboard.component';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-boardspage',
  imports: [
    HeaderComponent,
    MatDialogModule,
    MatButtonModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    RouterLink,
  ],
  templateUrl: './boardspage.component.html',
  styleUrl: './boardspage.component.css',
})
export class BoardspageComponent {
  loginService = inject(LoginService);
  boardsService = inject(BoardControllerService);
  boards: BoardDTO[] = [];
  user!: UserDTO | null;
  router = inject(Router);
  readonly dialog = inject(MatDialog);

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

  openCreateBoardDialog(): void {
    this.dialog.open(CreateboardComponent, { data: this.boards });
  }

  deleteBoard(id: number | undefined): void {
    if (id && this.user?.idUser) {
      this.boardsService.deleteBoard(id, this.user.idUser).subscribe();
      this.boards = this.boards.filter((board) => board.idBoard !== id);
    }
  }
}
