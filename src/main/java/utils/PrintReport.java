package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.TableModel;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ngocd
 */
public class PrintReport {

    public static void printExcel(JTable table, File file) {
        try {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Report");
//            tạo header của cột
            Row r = sheet.createRow(0);
            TableModel model = table.getModel();
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = r.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }

//            tạo dữ liẹt của bảng
            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(model.getValueAt(i, j).toString());
                }
            }
            FileOutputStream fos = new FileOutputStream(file + ".xlsx");
            wb.write(fos);
            fos.close();
            wb.close();

            if (DialogHelper.confirm(null, "Lưu thàng công. Bạn có muốn mở file lên không?")) {
                Desktop.getDesktop().open(new File(file + ".xlsx"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printPhieuNhapPDF(JTable table, String maPN, Date ngayNhap, String nhanVien, String nhaCungCap, double tongTien) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data/nhaphang/PhieuNhap_" + maPN + ".pdf"));
            document.open();
            BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);
            Font fontBold = new Font(bf, 14, Font.BOLD);
            Font fontTitle = new Font(bf, 30, Font.BOLD);
            Paragraph p = new Paragraph("THÔNG TIN PHIẾU NHẬP", fontBold);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            p = new Paragraph("Mã phiếu nhập: " + maPN, font);
            document.add(p);
            p = new Paragraph("Ngày nhập: " + XDate.toString(ngayNhap, "dd/MM/yyyy HH:mm:ss"), font);
            document.add(p);
            p = new Paragraph("Nhân viên: " + nhanVien, font);
            document.add(p);
            p = new Paragraph("Nhà cung cấp: " + nhaCungCap, font);
            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            PdfPTable t = new PdfPTable(table.getColumnCount());
            float[] columnWidths = new float[table.getColumnCount()];
            for (int i = 0; i < table.getColumnCount(); i++) {
                t.addCell(new Phrase(table.getColumnName(i), fontBold));
                columnWidths[i] = 100f;
            }
            t.setWidthPercentage(columnWidths, document.getPageSize());
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    t.addCell(new Phrase(table.getValueAt(i, j).toString(), font));
                }
            }
            document.add(t);
            p = new Paragraph("Tổng tiền: " + MoneyFormat.format(tongTien), font);
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            document.close();
            if (DialogHelper.confirm(null, "Lưu thàng công. Bạn có muốn mở file lên không?")) {
                Desktop.getDesktop().open(new File("data/nhaphang/PhieuNhap_" + maPN + ".pdf"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void printHoaDonPDF(JTable table, String maHD, Date ngayNhap, String nhanVien, int maThanhVien, double tongTien) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("data/hoadon/HoaDon_" + maHD + ".pdf"));
            document.open();
            BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);
            Font fontBold = new Font(bf, 14, Font.BOLD);
            Font fontTitle = new Font(bf, 30, Font.BOLD);
            Paragraph p = new Paragraph("THÔNG TIN HÓA ĐƠN", fontBold);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            p = new Paragraph("Mã HÓA ĐƠN: " + maHD, font);
            document.add(p);
            p = new Paragraph("Ngày nhập: " + XDate.toString(ngayNhap, "dd/MM/yyyy HH:mm:ss"), font);
            document.add(p);
            p = new Paragraph("Nhân viên: " + nhanVien, font);
            document.add(p);
            p = new Paragraph("MÃ THÀNH VIÊN: " + maThanhVien, font);
            document.add(p);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            PdfPTable t = new PdfPTable(table.getColumnCount());
            float[] columnWidths = new float[table.getColumnCount()];
            for (int i = 0; i < table.getColumnCount(); i++) {
                t.addCell(new Phrase(table.getColumnName(i), fontBold));
                columnWidths[i] = 100f;
            }
            t.setWidthPercentage(columnWidths, document.getPageSize());
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    t.addCell(new Phrase(table.getValueAt(i, j).toString(), font));
                }
            }
            document.add(t);
            p = new Paragraph("Tổng tiền: " + MoneyFormat.format(tongTien), font);
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            document.close();
            if (DialogHelper.confirm(null, "Lưu thàng công. Bạn có muốn mở file lên không?")) {
                Desktop.getDesktop().open(new File("data/hoadon/HoaDon_" + maHD + ".pdf"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
