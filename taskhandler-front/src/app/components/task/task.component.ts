import { Component, inject, Input } from '@angular/core';
import { TaskDTO, UserDTO } from '../../../../api';
import { MatDialog } from '@angular/material/dialog';
import { TaskdialogComponent } from '../dialog/taskdialog/taskdialog.component';
import { DatePipe } from '@angular/common';
import { TaskDataType } from '../../types/TaskDataType';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-task',
  imports: [DatePipe, MatTooltipModule],
  templateUrl: './task.component.html',
  styleUrl: './task.component.css',
})
export class TaskComponent {
  @Input({ required: true }) task!: TaskDTO;
  @Input({ required: true }) users!: UserDTO[] | undefined;
  readonly dialog = inject(MatDialog);

  onClick(): void {
    if (this.users) {
      const data: TaskDataType = {
        task: this.task,
        users: this.users,
      };
      this.dialog.open(TaskdialogComponent, {
        data: data,
        maxWidth: '100%',
      });
    }
  }
}
