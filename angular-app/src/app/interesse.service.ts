import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Interesse } from './interesse';


@Injectable({
  providedIn: 'root'
})
export class InteresseService {

  constructor(
    private http: HttpClient
  ) { }

  getInteresses(): Observable<Interesse[]> {
    return this.http.get<Interesse[]>('http://localhost:8080/rest/interesses');
  }
}
