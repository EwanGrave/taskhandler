import { Component, inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { TaskControllerService, TaskDTO, UserDTO } from '../../../../../api';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTooltipModule } from '@angular/material/tooltip';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { TaskDataType } from '../../../types/TaskDataType';

@Component({
  selector: 'app-taskdialog',
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatCheckboxModule,
    MatTooltipModule,
    ReactiveFormsModule,
  ],
  templateUrl: './taskdialog.component.html',
  styleUrl: './taskdialog.component.css',
})
export class TaskdialogComponent {
  readonly dialogRef = inject(MatDialogRef<TaskdialogComponent>);
  data = inject<TaskDataType>(MAT_DIALOG_DATA);
  task!: TaskDTO;
  users!: UserDTO[];
  taskService = inject(TaskControllerService);

  showCheckboxes: boolean = false;

  dueDateForm = new FormGroup({
    date: new FormControl<string>('', Validators.required),
  });

  ngOnInit(): void {
    this.task = this.data.task;
    this.users = this.data.users;

    if (this.task.dueDate)
      this.dueDateForm.setValue({ date: this.task.dueDate.substring(0, 10) });
  }

  setTaskDone(done: boolean): void {
    const newTask: TaskDTO = {
      idTask: this.task.idTask,
      title: this.task.title,
      description: this.task.description,
      done: done,
      checkitems: this.task.checkitems,
    };
    this.taskService.updateTask(newTask).subscribe();
    this.task.done = done;
  }

  changeDueDate(): void {
    if (this.dueDateForm.valid && this.dueDateForm.value.date) {
      this.task.dueDate = this.dueDateForm.value.date;
      this.taskService.updateTask(this.task).subscribe();
    }
  }

  deleteTask(): void {
    if (this.task.idTask) {
      this.taskService.deleteTask(this.task.idTask).subscribe();
    }
  }

  changeShowCheckboxes(): void {
    this.showCheckboxes = !this.showCheckboxes;
  }

  handleMembers(user: UserDTO): void {
    if (this.task.idTask && user.idUser) {
      if (this.isUserInTask(user)) {
        this.taskService.removeUser(this.task.idTask, user.idUser).subscribe();
        this.task.users = this.task.users?.filter(
          (u) => u.idUser !== user.idUser
        );
      } else {
        this.taskService.addUser(this.task.idTask, user.idUser).subscribe();
        this.task.users?.push(user);
      }
    }
  }

  isUserInTask(user: UserDTO): boolean {
    return this.task.users?.find((u) => u.idUser === user.idUser) !== undefined;
  }
}
