import { Injectable } from '@angular/core';
import {lastValueFrom, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ShortenUrlService {

  constructor(private http: HttpClient) {
  }

  storeUrl(pageUrl: string): Promise<{ response:string }> {
    return lastValueFrom(this.http.post<{ response:string }>("http://localhost:8080/api/urls",pageUrl));
  }
}
