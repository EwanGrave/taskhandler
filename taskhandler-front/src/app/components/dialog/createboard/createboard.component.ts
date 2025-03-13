import { Component, inject, Input } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButton } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BoardControllerService, BoardDTO } from '../../../../../api';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-createboard',
  imports: [
    MatDialogModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButton,
  ],
  templateUrl: './createboard.component.html',
  styleUrl: './createboard.component.css',
})
export class CreateboardComponent {
  loginService = inject(LoginService);
  boardService = inject(BoardControllerService);

  boardFormGroup = new FormGroup({
    name: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
    ]),
  });

  onSubmit(): void {
    const user = this.loginService.getLoggedUser();
    if (this.boardFormGroup.valid && user) {
      const board: BoardDTO = {
        name: this.boardFormGroup.value.name ?? '',
        users: [user],
        tasklists: [],
      };
      this.boardService.createBoard(board).subscribe();
    }
  }
}
