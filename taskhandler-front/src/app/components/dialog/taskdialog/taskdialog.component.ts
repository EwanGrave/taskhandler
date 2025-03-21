import { Component, inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { TaskControllerService, TaskDTO } from '../../../../../api';

@Component({
  selector: 'app-taskdialog',
  imports: [MatDialogModule],
  templateUrl: './taskdialog.component.html',
  styleUrl: './taskdialog.component.css',
})
export class TaskdialogComponent {
  readonly dialogRef = inject(MatDialogRef<TaskdialogComponent>);
  task = inject<TaskDTO>(MAT_DIALOG_DATA);
  taskService = inject(TaskControllerService);
}
