import { Component, inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { TaskControllerService, TaskDTO } from '../../../../../api';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-taskdialog',
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatCheckboxModule,
    MatTooltipModule,
  ],
  templateUrl: './taskdialog.component.html',
  styleUrl: './taskdialog.component.css',
})
export class TaskdialogComponent {
  readonly dialogRef = inject(MatDialogRef<TaskdialogComponent>);
  task = inject<TaskDTO>(MAT_DIALOG_DATA);
  taskService = inject(TaskControllerService);

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
}
