import { Component, inject } from '@angular/core';
import { BoardControllerService, BoardDTO, UserDTO } from '../../../../api';
import { LoginService } from '../../services/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HeaderComponent } from '../../components/header/header/header.component';

@Component({
  selector: 'app-boardpage',
  imports: [HeaderComponent],
  templateUrl: './boardpage.component.html',
  styleUrl: './boardpage.component.css',
})
export class BoardpageComponent {
  boardService = inject(BoardControllerService);
  loginService = inject(LoginService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  idBoard!: number;
  board!: BoardDTO;
  user!: UserDTO | null;

  ngOnInit(): void {
    this.user = this.loginService.getLoggedUser();
    if (!this.user) {
      this.router.navigate(['/login']);
    }

    this.route.params.subscribe((params) => {
      this.idBoard = params['id'];
    });

    this.boardService
      .getBoardById(this.idBoard)
      .subscribe((board) => (this.board = board));
  }
}
