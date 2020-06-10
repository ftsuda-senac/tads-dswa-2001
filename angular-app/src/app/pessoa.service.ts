import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PagedResult } from './pagedResult';
import { Pessoa } from './pessoa';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient
  ) { }

  getPagedResult(pagina: number = 0, qtd: number = 10) : Observable<PagedResult<Pessoa>> {
    return this.http.get<PagedResult<Pessoa>>('http://localhost:8080/rest/pessoas?pagina=' + pagina + '&qtd=' + qtd);
  }

  getPessoaById(id: number) : Observable<Pessoa> {
    return this.http.get<Pessoa>('http://localhost:8080/rest/pessoas/' + id);
  }

  addNew(pessoa: Pessoa) : Observable<void> {
    return this.http.post<void>('http://localhost:8080/rest/pessoas', pessoa, this.httpOptions);
  }

  deleteById(id: number) : Observable<void> {
    return this.http.delete<void>('http://localhost:8080/rest/pessoas/' + id)
  }

}
