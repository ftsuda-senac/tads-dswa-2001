import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PessoaService } from '../pessoa.service';
import { Pessoa } from '../pessoa';
import { ModalInfo } from '../modalInfo';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  pessoas: Pessoa[] = [
    {
      id: 1,
      nome: 'Fulano',
      sexo: 1,
      dataNascimento: new Date('2000-01-01'),
      interessesId: [1, 2]
    },
    {
      id: 2,
      nome: 'Ciclana',
      sexo: 0,
      dataNascimento: new Date('2000-02-02'),
      interessesId: [3, 4]
    }
  ];
  paginaAtual: number = 0;
  ultimaPagina: number = 0;
  qtd: number = 10;
  first: boolean = true;
  last: boolean = true;

  modalInfo: ModalInfo = {
    mensagem: '',
    itemId: 0
  }

  constructor(
    private pessoaService : PessoaService,
    private modalService : NgbModal
  ) { }

  ngOnInit(): void {
    this.listar();
  }

  listar(): void {
    this.pessoaService.getPagedResult(this.paginaAtual, this.qtd).subscribe(resultado => {
      this.pessoas = resultado.content;
      this.paginaAtual = resultado.number;
      this.ultimaPagina = resultado.totalPages - 1;
      this.first = resultado.first;
      this.last = resultado.last;
    });
  }

  hasPaginaAnterior(): boolean {
    return (this.paginaAtual - 1) >= 0;
  }

  hasProximaPagina(): boolean {
    return (this.paginaAtual + 1) <= this.ultimaPagina;
  }

  goPaginaAnterior(ev: any): void {
    ev.preventDefault();
    if (this.hasPaginaAnterior) {
      this.paginaAtual--;
      this.listar();
    }
  }

  goProximaPagina(ev: any): void {
    ev.preventDefault();
    if (this.hasProximaPagina) {
      this.paginaAtual++;
      this.listar();
    }
  }

  goPrimeiraPagina(ev: any): void {
    ev.preventDefault();
    this.paginaAtual = 0;
    this.listar();
  }

  goUltimaPagina(ev: any): void {
    ev.preventDefault();
    this.paginaAtual = this.ultimaPagina;
    this.listar();
  }

  abrirModalDelete(modalDelete: any, id: number) {
    this.modalInfo = {
      mensagem: 'Confirma remoção da pessoa ID ' + id + '?',
      itemId: id
    }
    this.modalService.open(modalDelete);
  }

  deletePessoa() {
    this.pessoaService.deleteById(this.modalInfo.itemId).subscribe(() => {
      this.modalInfo = {
        mensagem: '',
        itemId: 0
      }
      this.listar();
      this.modalService.dismissAll();
    });
  }
}
