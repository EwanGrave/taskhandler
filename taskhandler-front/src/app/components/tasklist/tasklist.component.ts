import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { TaskControllerService, TaskDTO, TasklistDTO } from '../../../../api';
import { TaskComponent } from '../task/task.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {
  FormGroup,
  FormControl,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { DndModule, EffectAllowed } from 'ngx-drag-drop';

@Component({
  selector: 'app-tasklist',
  imports: [
    TaskComponent,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    ReactiveFormsModule,
    DndModule,
  ],
  templateUrl: './tasklist.component.html',
  styleUrl: './tasklist.component.css',
})
export class TasklistComponent {
  taskService = inject(TaskControllerService);

  @Input({ required: true }) tasklist!: TasklistDTO;
  @Output() onTasklistDelete = new EventEmitter<number>();
  hideNewTaskForm: boolean = true;

  taskForm = new FormGroup({
    name: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(15),
    ]),
  });

  draggable = {
    effectAllowed: 'all' as EffectAllowed,
    disable: false,
    handle: false,
  };

  setHideNewTaskForm(value: boolean): void {
    this.hideNewTaskForm = value;
  }

  createTask(): void {
    if (this.taskForm.valid && this.tasklist.idTasklist) {
      const task: TaskDTO = {
        title: this.taskForm.value.name ?? '',
        description: '',
        checkitems: [],
        users: [],
      };

      this.taskService
        .createTask(this.tasklist.idTasklist, task)
        .subscribe((newTask) => this.tasklist.tasks?.push(newTask));

      this.setHideNewTaskForm(true);
    }
  }

  deleteTasklist(): void {
    if (this.tasklist.idTasklist) {
      this.onTasklistDelete.emit(this.tasklist.idTasklist);
    }
  }
}
