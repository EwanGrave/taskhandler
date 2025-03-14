import { Component, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButton } from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
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
  readonly dialogRef = inject(MatDialogRef<CreateboardComponent>);
  boards = inject<BoardDTO[]>(MAT_DIALOG_DATA);
  loginService = inject(LoginService);
  boardService = inject(BoardControllerService);

  boardFormGroup = new FormGroup({
    name: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(20),
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
      this.boardService.createBoard(board).subscribe((newBoard) => {
        this.boards.push(newBoard);
      });
      this.dialogRef.close();
    }
  }
}
