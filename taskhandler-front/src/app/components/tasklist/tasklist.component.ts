import { Component, Input } from '@angular/core';
import { TasklistDTO } from '../../../../api';
import { TaskComponent } from '../task/task.component';

@Component({
  selector: 'app-tasklist',
  imports: [TaskComponent],
  templateUrl: './tasklist.component.html',
  styleUrl: './tasklist.component.css',
})
export class TasklistComponent {
  @Input({ required: true }) tasklist!: TasklistDTO;
}
