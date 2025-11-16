 package com.example.clinica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clinica.DatabaseBackupService;

@RestController
@RequestMapping("/api/backup")
public class BackupController {

    @Autowired
    private DatabaseBackupService backupService;

    // Ahora acepta GET y POST para facilitar pruebas
    @RequestMapping(value = "/manual", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseEntity<String> createManualBackup() {
        try {
            String result = backupService.createManualBackup();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error al crear backup: " + e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getBackupStatus() {
        return ResponseEntity.ok("Backup automático todos los sabados a las 4:00 AM.");
    }
}
