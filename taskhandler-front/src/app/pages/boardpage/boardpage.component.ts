import { Component, inject } from '@angular/core';
import {
  BoardControllerService,
  BoardDTO,
  TaskControllerService,
  TaskDTO,
  TasklistControllerService,
  TasklistDTO,
  UserDTO,
} from '../../../../api';
import { LoginService } from '../../services/login.service';
import { ActivatedRoute, Router } from '@angular/router';
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
import { HeaderComponent } from '../../components/header/header.component';
import { DndDropEvent, DndModule } from 'ngx-drag-drop';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialog } from '@angular/material/dialog';
import { AdduserdialogComponent } from '../../components/dialog/adduserdialog/adduserdialog.component';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-boardpage',
  imports: [
    HeaderComponent,
    TasklistComponent,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    DndModule,
    MatTooltipModule,
    MatMenuModule,
    MatIconModule,
  ],
  templateUrl: './boardpage.component.html',
  styleUrl: './boardpage.component.css',
})
export class BoardpageComponent {
  boardService = inject(BoardControllerService);
  tasklistService = inject(TasklistControllerService);
  taskService = inject(TaskControllerService);
  loginService = inject(LoginService);
  router = inject(Router);
  route = inject(ActivatedRoute);
  snack = inject(MatSnackBar);
  readonly dialog = inject(MatDialog);

  idBoard!: number;
  board!: BoardDTO;
  user!: UserDTO | null;
  hideNewListForm: boolean = true;
  hideUpdateBoardForm: boolean = true;

  tasklistForm = new FormGroup({
    name: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(15),
    ]),
  });

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

  setHideUpdateBoardForm(value: boolean): void {
    this.hideUpdateBoardForm = value;
    this.boardForm.setValue({ name: this.board.name ?? '' });
  }

  updateBoard(): void {
    if (this.boardForm.valid && this.user?.idUser) {
      this.board.name = this.boardForm.value.name ?? '';
      this.boardService.updateBoard(this.user.idUser, this.board).subscribe();
      this.setHideUpdateBoardForm(true);
      this.snack.open('Board mis à jour', 'Fermer');
    }
  }

  createTasklist(): void {
    if (this.tasklistForm.valid) {
      const tasklist: TasklistDTO = {
        name: this.tasklistForm.value.name ?? '',
        tasks: [],
      };

      this.tasklistService
        .createTasklist(this.idBoard, tasklist)
        .subscribe((newTasklist) => this.board.tasklists?.push(newTasklist));

      this.setHideNewListForm(true);
      this.tasklistForm.reset();
      this.snack.open('Liste créée', 'Fermer');
    }
  }

  deleteTaskList(idTasklist: number) {
    this.tasklistService.deleteTasklist(idTasklist).subscribe();
    this.board.tasklists = this.board.tasklists?.filter(
      (tasklist) => tasklist.idTasklist !== idTasklist
    );
    this.snack.open('Liste supprimée', 'Fermer');
  }

  onDrop(event: DndDropEvent, list: TasklistDTO) {
    const task: TaskDTO = event.data.task;
    const oldList: TasklistDTO =
      this.board.tasklists?.find((l) => l.idTasklist === event.data.listId) ??
      {};

    if (!list.tasks?.includes(task) && list.idTasklist) {
      this.taskService.changeTasklist(list.idTasklist, task).subscribe();

      oldList.tasks = oldList.tasks?.filter((t) => t.idTask !== task.idTask);
      list.tasks?.push(task);
    }
  }

  openAddUserDialog(): void {
    this.dialog.open(AdduserdialogComponent, { data: this.board });
  }
}
