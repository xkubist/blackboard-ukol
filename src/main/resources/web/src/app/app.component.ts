import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ShortenUrlService} from "./services/shortenurl.service";
import {HttpErrorResponse} from "@angular/common/http";
import { ClipboardService } from 'ngx-clipboard';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isShortened: boolean;
  form: FormGroup;

  constructor(private fb: FormBuilder, private shortUrlService: ShortenUrlService ,private clipboardApi: ClipboardService) {
    this.isShortened = false;
    this.form = fb.group({
      fullLink: ['', Validators.required]
    })
  }

  async ngOnInit(): Promise<void> {
  }

  async onSubmit(): Promise<void> {
    try {
      let promise: Promise<{ response: string }> = this.shortUrlService.storeUrl(this.form.get('fullLink')?.value);
      let shortenUrl: { response: string } = await promise;
      this.form.controls['fullLink'].setValue(shortenUrl.response);
      this.form.controls['fullLink'].disable();
      this.isShortened = true;
    } catch (error) {
      if (error instanceof HttpErrorResponse && (error as HttpErrorResponse).status===400) {
        alert("Error " + (error as HttpErrorResponse).status+": Given http is not valid");
      }
      else throw error;
    }
  }

  close() {
    this.form.reset();
    this.isShortened = false;
    this.form.controls['fullLink'].enable();
  }

  copy(value: string) {
    console.log("copy");
    console.log(value);
    this.clipboardApi.copyFromContent(value);
  }
}
