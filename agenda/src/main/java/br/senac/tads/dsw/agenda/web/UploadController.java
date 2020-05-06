package br.senac.tads.dsw.agenda.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@GetMapping
	public String abrirFormulario() {
		return "upload-form";
	}

	@PostMapping("/salvar")
	public String salvarArquivo(@RequestParam("arquivo") MultipartFile arquivo,
			RedirectAttributes redirAttr) {
		
		if (arquivo.isEmpty()) {
			// ERRO
			redirAttr.addFlashAttribute("msg", "Arquivo inválido");
			return "redirect:/upload";
		}
		try {
			byte[] bytesArquivo = arquivo.getBytes();
			
			Path diretorioDestino = Paths.get("C:/senac/tads/uploads/" + arquivo.getOriginalFilename());
			Files.write(diretorioDestino, bytesArquivo);
			
			redirAttr.addFlashAttribute("msg", "Arquivo " + arquivo.getOriginalFilename() + " salvo com sucesso");
			redirAttr.addFlashAttribute("pathImagem", "/acesso-upload/" + arquivo.getOriginalFilename());
		} catch (IOException e) {
			redirAttr.addFlashAttribute("msg", "Erro nos bytes recebidos -" + e.getMessage());

		}
		return "redirect:/upload";	
	}
	

}
