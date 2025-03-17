import { Component, inject, Input } from '@angular/core';
import { TaskDTO } from '../../../../api';
import { MatDialog } from '@angular/material/dialog';
import { TaskdialogComponent } from '../dialog/taskdialog/taskdialog.component';

@Component({
  selector: 'app-task',
  imports: [],
  templateUrl: './task.component.html',
  styleUrl: './task.component.css',
})
export class TaskComponent {
  @Input({ required: true }) task!: TaskDTO;
  readonly dialog = inject(MatDialog);

  onClick(): void {
    this.dialog.open(TaskdialogComponent, { data: this.task });
  }
}
