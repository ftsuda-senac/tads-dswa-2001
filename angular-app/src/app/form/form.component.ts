import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PessoaService } from '../pessoa.service';
import { Pessoa } from '../pessoa';
import { InteresseService } from '../interesse.service';
import { Interesse } from '../interesse';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  interesses: Interesse[] = [];

  id: number = 0;

  titulo: string = 'Inclusão de pessoa';

  pessoa: Pessoa = {
    nome: '',
    sexo: 0,
    dataNascimento: null,
    interessesId: []
  }

  constructor(
    private pessoaService: PessoaService,
    private interesseService : InteresseService,
    private activateRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadInteresses();
    // Recupera o valor do ID da rota.
    this.activateRoute.paramMap.subscribe(params => {
      if (params.get('id')) {
        this.id = Number(params.get('id'));
        this.titulo = 'Editar pessoa';
        this.getPessoaById(this.id);
      }
    });
  }

  loadInteresses(): void {
    this.interesseService.getInteresses().subscribe(interessesRetornado => {
      this.interesses = interessesRetornado;
      for (let interesse of this.interesses) {
        interesse.selecionado = false;
      }
    });
  }

  getPessoaById(id: number) {
    this.pessoaService.getPessoaById(id).subscribe(pessoaRetornado => {
      this.pessoa = pessoaRetornado;
      for (let interesse of this.interesses) {
        for (let idSelecionado of this.pessoa.interessesId) {
          if (interesse.id === idSelecionado) {
            interesse.selecionado = true;
            break;
          }
        }
      }
    });
  }

  salvar() {
    let interessesId = [];
    for (const interesse of this.interesses) {
      if (interesse.selecionado) {
        interessesId.push(interesse.id);
      }
    }
    this.pessoa.interessesId = interessesId;
    console.log("*** DADOS ENVIADOS: " + JSON.stringify(this.pessoa));
    this.pessoaService.addNew(this.pessoa).subscribe();
    this.router.navigate(['/']);
  }

}
