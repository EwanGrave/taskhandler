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
  selector: 'app-adduserdialog',
  imports: [
    MatDialogModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButton,
  ],
  templateUrl: './adduserdialog.component.html',
  styleUrl: './adduserdialog.component.css',
})
export class AdduserdialogComponent {
  readonly dialogRef = inject(MatDialogRef<AdduserdialogComponent>);
  board = inject<BoardDTO>(MAT_DIALOG_DATA);
  loginService = inject(LoginService);
  boardService = inject(BoardControllerService);
  showError: boolean = false;

  addUserFormGroup = new FormGroup({
    username: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(20),
    ]),
  });

  onSubmit(): void {
    const user = this.loginService.getLoggedUser();
    if (this.addUserFormGroup.valid && user?.idUser && this.board.idBoard) {
      this.boardService
        .addMembership(
          this.board.idBoard,
          this.addUserFormGroup.value.username ?? ''
        )
        .subscribe({
          error: (e) => (this.showError = true),
          complete: () => this.dialogRef.close(),
        });
    }
  }
}
