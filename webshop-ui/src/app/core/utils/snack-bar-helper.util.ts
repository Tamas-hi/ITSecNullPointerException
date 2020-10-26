import { MatSnackBar, MatSnackBarConfig, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';

export class SnackBarHelperUtil {

    public static showMessage(
        matSnackBar: MatSnackBar,
        message: string,
        isError?: boolean,
        duration?: number,
        action?: string
    ): MatSnackBarRef<SimpleSnackBar> {
        if (!action) {
            action = 'OK';
        }

        if (!duration && (duration !== 0)) {
            duration = 3000;
        }

        const config: MatSnackBarConfig = {
            verticalPosition: 'top'
        };

        if (duration > 0) {
            config.duration = duration;
        }

        if (isError === true) {
            config.panelClass = ['message-box', 'error'];
        } else {
            config.panelClass = ['message-box', 'success'];
        }

        return matSnackBar.open(message, action, config);
    }
}
