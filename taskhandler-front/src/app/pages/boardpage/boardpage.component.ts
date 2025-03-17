import { Component, inject } from '@angular/core';
import {
  BoardControllerService,
  BoardDTO,
  TasklistControllerService,
  TasklistDTO,
  UserDTO,
} from '../../../../api';
import { LoginService } from '../../services/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HeaderComponent } from '../../components/header/header/header.component';
import { TasklistComponent } from '../../components/tasklist/tasklist.component';
import { MatButtonModule } from '@angular/material/button';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-boardpage',
  imports: [
    HeaderComponent,
    TasklistComponent,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './boardpage.component.html',
  styleUrl: './boardpage.component.css',
})
export class BoardpageComponent {
  boardService = inject(BoardControllerService);
  tasklistService = inject(TasklistControllerService);
  loginService = inject(LoginService);
  router = inject(Router);
  route = inject(ActivatedRoute);

  idBoard!: number;
  board!: BoardDTO;
  user!: UserDTO | null;
  hideNewListForm: boolean = true;

  boardForm = new FormGroup({
    name: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(15),
    ]),
  });

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

  setHideNewListForm(value: boolean): void {
    this.hideNewListForm = value;
  }

  createTasklist(): void {
    if (this.boardForm.valid) {
      const tasklist: TasklistDTO = {
        name: this.boardForm.value.name ?? '',
        tasks: [],
      };

      this.tasklistService
        .createTasklist(this.idBoard, tasklist)
        .subscribe((newTasklist) => this.board.tasklists?.push(newTasklist));

      this.setHideNewListForm(true);
    }
  }
}
