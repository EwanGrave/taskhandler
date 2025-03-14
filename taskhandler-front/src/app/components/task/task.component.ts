import { Component, Input } from '@angular/core';
import { TaskDTO } from '../../../../api';

@Component({
  selector: 'app-task',
  imports: [],
  templateUrl: './task.component.html',
  styleUrl: './task.component.css',
})
export class TaskComponent {
  @Input({ required: true }) task!: TaskDTO;
}
