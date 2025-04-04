import { Component, inject, SimpleChange } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { TaskControllerService, TaskDTO } from '../../../../../api';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatTooltipModule } from '@angular/material/tooltip';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

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
  task = inject<TaskDTO>(MAT_DIALOG_DATA);
  taskService = inject(TaskControllerService);

  dueDateForm = new FormGroup({
    date: new FormControl<string>('', Validators.required),
  });

  ngOnInit(): void {
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
}
