package com.example.clinica;

import java.io.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class PdfService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    public byte[] generarReporteMedicoPdf(ReporteMedico rep) {
        Context ctx = new Context();
        ctx.setVariable("rep", rep);

        // Render Thymeleaf a HTML
        String html = templateEngine.process("reportesPdf", ctx);

        // Convertir HTML a PDF
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null); // baseURL null al usar contenido embebido
            builder.toStream(baos);
            builder.run();
            return baos.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("Error generando PDF del reporte médico", ex);
        }
    }
}
