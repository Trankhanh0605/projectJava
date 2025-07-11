-- Chuyển sang dùng database có sẵn
USE StudentDB;
GO

-- Tạo bảng Student nếu chưa tồn tại
IF OBJECT_ID('dbo.Student','U') IS NULL
BEGIN
    CREATE TABLE dbo.Student (
        StudentID  VARCHAR(10)    PRIMARY KEY,    -- mã sinh viên
        FullName   NVARCHAR(100),                 -- họ tên
        ClassName  NVARCHAR(20),                  -- lớp
        BirthDate  DATE,                          -- ngày sinh
        Gender     NVARCHAR(10)                   -- giới tính
    );
END
GO

-- Tạo bảng Subject nếu chưa tồn tại
IF OBJECT_ID('dbo.Subject','U') IS NULL
BEGIN
    CREATE TABLE dbo.Subject (
        SubjectID   VARCHAR(10)    PRIMARY KEY,  -- mã môn học
        SubjectName NVARCHAR(100),                -- tên môn học
        Credit      INT                           -- số tín chỉ
    );
END
GO

-- Tạo bảng Transcript nếu chưa tồn tại
IF OBJECT_ID('dbo.Transcript','U') IS NULL
BEGIN
    CREATE TABLE dbo.Transcript (
        SubjectID  VARCHAR(10),                  -- mã môn học
        StudentID  VARCHAR(10),                  -- mã sinh viên
        Score      DECIMAL(3,1),                 -- điểm
        PRIMARY KEY (StudentID, SubjectID),
        FOREIGN KEY (StudentID) REFERENCES dbo.Student(StudentID),
        FOREIGN KEY (SubjectID) REFERENCES dbo.Subject(SubjectID)
    );
END
GO

-- Chèn dữ liệu vào bảng Student (có thể chạy nhiều lần mà không lỗi nếu khoá chính đã tồn tại)
INSERT INTO dbo.Student (StudentID, FullName, ClassName, BirthDate, Gender)
SELECT v.StudentID, v.FullName, v.ClassName, v.BirthDate, v.Gender
FROM (VALUES
    ('SV001', N'Nguyễn Văn An',      N'KTPM K20', '2003-05-15', N'Nam'),
    ('SV002', N'Trần Thị Bình',      N'HTTT K21', '2004-01-20', N'Nữ'),
    -- … tiếp các dòng khác …
    ('SV003', N'Lê Văn Cường', N'CNTT K19', '2002-11-01', N'Nam'),
('SV004', N'Phạm Thị Dung', N'KTPM K20', '2003-08-22', N'Nữ'),
('SV005', N'Hoàng Minh Đức', N'HTTT K21', '2004-03-10', N'Nam'),
('SV006', N'Đỗ Thị Giang', N'CNTT K19', '2002-09-05', N'Nữ'),
('SV007', N'Nguyễn Thanh Hải', N'KTPM K20', '2003-06-30', N'Nam'),
('SV008', N'Bùi Thị Hương', N'HTTT K21', '2004-02-14', N'Nữ'),
('SV009', N'Vũ Văn Khoa', N'CNTT K19', '2002-10-25', N'Nam'),
('SV010', N'Đặng Thị Lan', N'KTPM K20', '2003-07-07', N'Nữ'),
('SV011', N'Trần Văn Long', N'HTTT K21', '2004-04-18', N'Nam'),
('SV012', N'Nguyễn Thị Mai', N'CNTT K19', '2002-12-03', N'Nữ'),
('SV013', N'Phạm Văn Nam', N'KTPM K20', '2003-09-11', N'Nam'),
('SV014', N'Lê Thị Oanh', N'HTTT K21', '2004-05-29', N'Nữ'),
('SV015', N'Hoàng Văn Phúc', N'CNTT K19', '2002-08-16', N'Nam'),
('SV016', N'Đỗ Thanh Quyên', N'KTPM K20', '2003-04-02', N'Nữ'),
('SV017', N'Nguyễn Minh Quang', N'HTTT K21', '2004-06-21', N'Nam'),
('SV018', N'Bùi Thị Thu', N'CNTT K19', '2002-07-13', N'Nữ'),
('SV019', N'Vũ Đức Trung', N'KTPM K20', '2003-03-08', N'Nam'),
('SV020', N'Đặng Thị Yến', N'HTTT K21', '2004-01-05', N'Nữ'),
('SV021', N'Nguyễn Văn Kiên', N'KTPM K20', '2003-02-28', N'Nam'),
('SV022', N'Trần Thị Loan', N'HTTT K21', '2004-08-19', N'Nữ'),
('SV023', N'Lê Văn Phát', N'CNTT K19', '2002-04-17', N'Nam'),
('SV024', N'Phạm Thị Kim', N'KTPM K20', '2003-10-06', N'Nữ'),
('SV025', N'Hoàng Minh Sang', N'HTTT K21', '2004-11-23', N'Nam'),
('SV026', N'Đỗ Thị Thảo', N'CNTT K19', '2002-01-09', N'Nữ'),
('SV027', N'Nguyễn Thanh Tùng', N'KTPM K20', '2003-12-12', N'Nam'),
('SV028', N'Bùi Thị Nguyệt', N'HTTT K21', '2004-07-03', N'Nữ'),
('SV029', N'Vũ Văn Thịnh', N'CNTT K19', '2002-03-27', N'Nam'),
('SV030', N'Đặng Thị Trà', N'KTPM K20', '2003-09-24', N'Nữ'),
('SV031', N'Nguyễn Văn Hùng', N'HTTT K21', '2004-09-01', N'Nam'),
('SV032', N'Trần Thị Thoa', N'CNTT K19', '2002-06-19', N'Nữ'),
('SV033', N'Lê Văn Khánh', N'KTPM K20', '2003-01-14', N'Nam'),
('SV034', N'Phạm Thị Liên', N'HTTT K21', '2004-10-28', N'Nữ'),
('SV035', N'Hoàng Minh An', N'CNTT K19', '2002-02-04', N'Nam'),
('SV036', N'Đỗ Thị Chi', N'KTPM K20', '2003-04-20', N'Nữ'),
('SV037', N'Nguyễn Thanh Bình', N'HTTT K21', '2004-03-07', N'Nam'),
('SV038', N'Bùi Thị Dung', N'CNTT K19', '2002-05-11', N'Nữ'),
('SV039', N'Vũ Văn Hiếu', N'KTPM K20', '2003-08-01', N'Nam'),
('SV040', N'Đặng Thị Loan', N'HTTT K21', '2004-07-25', N'Nữ'),
('SV041', N'Nguyễn Văn Long', N'CNTT K19', '2002-09-15', N'Nam'),
('SV042', N'Trần Thị Mai', N'KTPM K20', '2003-11-09', N'Nữ'),
('SV043', N'Lê Văn Nghĩa', N'HTTT K21', '2004-02-06', N'Nam'),
('SV044', N'Phạm Thị Ngọc', N'CNTT K19', '2002-12-08', N'Nữ'),
('SV045', N'Hoàng Minh Quyết', N'KTPM K20', '2003-05-03', N'Nam'),
('SV046', N'Đỗ Thị Thanh', N'HTTT K21', '2004-04-12', N'Nữ'),
('SV047', N'Nguyễn Thanh Tâm', N'CNTT K19', '2002-10-29', N'Nam'),
('SV048', N'Bùi Thị Trinh', N'KTPM K20', '2003-07-17', N'Nữ'),
('SV049', N'Vũ Văn Thành', N'HTTT K21', '2004-06-05', N'Nam'),
('SV050', N'Đặng Thị Kim Anh', N'CNTT K19', '2002-03-21', N'Nữ'),
('SV051', N'Nguyễn Văn Lợi', N'KTPM K20', '2003-09-02', N'Nam'),
('SV052', N'Trần Thị Ngọc', N'HTTT K21', '2004-01-30', N'Nữ'),
('SV053', N'Lê Văn Mạnh', N'CNTT K19', '2002-07-09', N'Nam'),
('SV054', N'Phạm Thị Thùy', N'KTPM K20', '2003-06-25', N'Nữ'),
('SV055', N'Hoàng Minh Khoa', N'HTTT K21', '2004-05-19', N'Nam'),
('SV056', N'Đỗ Thị Phượng', N'CNTT K19', '2002-04-14', N'Nữ'),
('SV057', N'Nguyễn Thanh Tùng', N'KTPM K20', '2003-03-01', N'Nam'),
('SV058', N'Bùi Thị Linh', N'HTTT K21', '2004-02-10', N'Nữ'),
('SV059', N'Vũ Văn Hải', N'CNTT K19', '2002-01-23', N'Nam'),
    ('SV060', N'Đặng Thị Thu',       N'KTPM K20', '2004-10-18', N'Nữ')
) AS v(StudentID, FullName, ClassName, BirthDate, Gender)
WHERE NOT EXISTS (
    SELECT 1 FROM dbo.Student s WHERE s.StudentID = v.StudentID
);
GO

-- Chèn dữ liệu vào bảng Subject
INSERT INTO dbo.Subject (SubjectID, SubjectName, Credit)
SELECT v.SubjectID, v.SubjectName, v.Credit
FROM (VALUES
    ('MH001', N'Lập trình hướng đối tượng', 3),
    ('MH002', N'Cơ sở dữ liệu',              3),
    -- … tiếp các dòng khác …
    ('MH003', N'Cấu trúc dữ liệu và giải thuật', 3),
('MH004', N'Hệ điều hành', 3),
('MH005', N'Mạng máy tính', 3),
('MH006', N'Công nghệ phần mềm', 3),
('MH007', N'Phân tích thiết kế hệ thống', 3),
('MH008', N'Kỹ thuật lập trình web', 3),
('MH009', N'Trí tuệ nhân tạo', 3),
('MH010', N'An toàn thông tin', 3),
('MH011', N'Lập trình di động', 3),
('MH012', N'Thực tập chuyên ngành', 2),
('MH013', N'Tiếng Anh chuyên ngành 1', 2),
('MH014', N'Tiếng Anh chuyên ngành 2', 2),
('MH015', N'Toán cao cấp A1', 3),
('MH016', N'Toán cao cấp A2', 3),
('MH017', N'Xác suất thống kê', 3),
('MH018', N'Kinh tế học đại cương', 2),
('MH019', N'Pháp luật đại cương', 2),
('MH020', N'Triết học Mác-Lênin', 3),
('MH021', N'Đường lối cách mạng Đảng Cộng sản Việt Nam', 3),
('MH022', N'Tư tưởng Hồ Chí Minh', 2),
('MH023', N'Giáo dục thể chất 1', 1),
('MH024', N'Giáo dục thể chất 2', 1),
('MH025', N'Giáo dục quốc phòng an ninh 1', 2),
('MH026', N'Giáo dục quốc phòng an ninh 2', 2),
('MH027', N'Lập trình Python cơ bản', 3),
('MH028', N'Lập trình Java nâng cao', 3),
('MH029', N'Quản trị cơ sở dữ liệu Oracle', 3),
    ('MH030', N'Hệ quản trị cơ sở dữ liệu phân tán', 3)
) AS v(SubjectID, SubjectName, Credit)
WHERE NOT EXISTS (
    SELECT 1 FROM dbo.Subject sub WHERE sub.SubjectID = v.SubjectID
);
GO

-- Chèn dữ liệu vào bảng Transcript
INSERT INTO dbo.Transcript (SubjectID, StudentID, Score)
SELECT v.SubjectID, v.StudentID, v.Score
FROM (VALUES
    ('MH001', 'SV001', 8.5), ('MH002', 'SV001', 7.8), ('MH003', 'SV001', 9.0), ('MH004', 'SV001', 7.2), ('MH005', 'SV001', 8.1),
('MH006', 'SV002', 6.9), ('MH007', 'SV002', 9.2), ('MH008', 'SV002', 4.9), ('MH009', 'SV002', 8.0), ('MH010', 'SV002', 6.5),
('MH011', 'SV003', 8.7), ('MH012', 'SV003', 9.1), ('MH013', 'SV003', 7.6), ('MH014', 'SV003', 8.2), ('MH015', 'SV003', 7.4),
('MH016', 'SV004', 6.8), ('MH017', 'SV004', 8.3), ('MH018', 'SV004', 7.7), ('MH019', 'SV004', 8.0), ('MH020', 'SV004', 6.0),
('MH021', 'SV005', 7.8), ('MH022', 'SV005', 8.1), ('MH023', 'SV005', 7.0), ('MH024', 'SV005', 9.1), ('MH025', 'SV005', 8.6),
('MH026', 'SV006', 7.2), ('MH027', 'SV006', 9.0), ('MH028', 'SV006', 7.5), ('MH029', 'SV006', 8.8), ('MH030', 'SV006', 6.7),
('MH001', 'SV007', 7.8), ('MH002', 'SV007', 7.9), ('MH003', 'SV007', 9.2), ('MH004', 'SV007', 7.1), ('MH005', 'SV007', 8.0),
('MH006', 'SV008', 3.3), ('MH007', 'SV008', 5.7), ('MH008', 'SV008', 8.0), ('MH009', 'SV008', 5.5), ('MH010', 'SV008', 6.9),
('MH011', 'SV009', 8.4), ('MH012', 'SV009', 7.6), ('MH013', 'SV009', 4.1), ('MH014', 'SV009', 6.8), ('MH015', 'SV009', 8.0),
('MH016', 'SV010', 7.5), ('MH017', 'SV010', 8.8), ('MH018', 'SV010', 6.5), ('MH019', 'SV010', 8.1), ('MH020', 'SV010', 4.3),

('MH021', 'SV011', 8.0), ('MH022', 'SV011', 7.4), ('MH023', 'SV011', 8.6), ('MH024', 'SV011', 7.9), ('MH025', 'SV011', 9.0),
('MH026', 'SV012', 6.7), ('MH027', 'SV012', 8.2), ('MH028', 'SV012', 7.4), ('MH029', 'SV012', 8.9), ('MH030', 'SV012', 6.9),
('MH001', 'SV013', 3.7), ('MH002', 'SV013', 9.1), ('MH003', 'SV013', 7.5), ('MH004', 'SV013', 8.8), ('MH005', 'SV013', 6.6),
('MH006', 'SV014', 8.1), ('MH007', 'SV014', 7.3), ('MH008', 'SV014', 8.7), ('MH009', 'SV014', 9.0), ('MH010', 'SV014', 7.4),
('MH011', 'SV015', 8.6), ('MH012', 'SV015', 7.9), ('MH013', 'SV015', 9.0), ('MH014', 'SV015', 7.1), ('MH015', 'SV015', 8.5),
('MH016', 'SV016', 6.7), ('MH017', 'SV016', 8.2), ('MH018', 'SV016', 7.4), ('MH019', 'SV016', 8.9), ('MH020', 'SV016', 6.9),
('MH021', 'SV017', 2.3), ('MH022', 'SV017', 4.7), ('MH023', 'SV017', 6.1), ('MH024', 'SV017', 5.5), ('MH025', 'SV017', 2.8),
('MH026', 'SV018', 6.6), ('MH027', 'SV018', 8.1), ('MH028', 'SV018', 7.3), ('MH029', 'SV018', 8.7), ('MH030', 'SV018', 9.0),
('MH001', 'SV019', 7.4), ('MH002', 'SV019', 8.6), ('MH003', 'SV019', 7.9), ('MH004', 'SV019', 9.0), ('MH005', 'SV019', 7.1),
('MH006', 'SV020', 8.5), ('MH007', 'SV020', 6.7), ('MH008', 'SV020', 8.2), ('MH009', 'SV020', 7.4), ('MH010', 'SV020', 8.9),

('MH011', 'SV021', 6.9), ('MH012', 'SV021', 8.3), ('MH013', 'SV021', 7.7), ('MH014', 'SV021', 9.1), ('MH015', 'SV021', 7.5),
('MH016', 'SV022', 8.8), ('MH017', 'SV022', 6.6), ('MH018', 'SV022', 8.1), ('MH019', 'SV022', 7.3), ('MH020', 'SV022', 8.7),
('MH021', 'SV023', 9.0), ('MH022', 'SV023', 7.4), ('MH023', 'SV023', 8.6), ('MH024', 'SV023', 3.9), ('MH025', 'SV023', 9.0),
('MH026', 'SV024', 7.1), ('MH027', 'SV024', 8.5), ('MH028', 'SV024', 6.7), ('MH029', 'SV024', 8.2), ('MH030', 'SV024', 7.4),
('MH001', 'SV025', 8.9), ('MH002', 'SV025', 4.9), ('MH003', 'SV025', 8.3), ('MH004', 'SV025', 7.7), ('MH005', 'SV025', 9.1),
('MH006', 'SV026', 7.5), ('MH007', 'SV026', 8.8), ('MH008', 'SV026', 6.6), ('MH009', 'SV026', 8.1), ('MH010', 'SV026', 7.3),
('MH011', 'SV027', 8.7), ('MH012', 'SV027', 9.0), ('MH013', 'SV027', 7.4), ('MH014', 'SV027', 8.6), ('MH015', 'SV027', 7.9),
('MH016', 'SV028', 9.0), ('MH017', 'SV028', 7.1), ('MH018', 'SV028', 8.5), ('MH019', 'SV028', 6.7), ('MH020', 'SV028', 8.2),
('MH021', 'SV029', 7.4), ('MH022', 'SV029', 8.9), ('MH023', 'SV029', 6.9), ('MH024', 'SV029', 8.3), ('MH025', 'SV029', 7.7),
('MH026', 'SV030', 2.1), ('MH027', 'SV030', 7.5), ('MH028', 'SV030', 8.8), ('MH029', 'SV030', 6.6), ('MH030', 'SV030', 8.1),

('MH001', 'SV031', 7.3), ('MH002', 'SV031', 8.7), ('MH003', 'SV031', 9.0), ('MH004', 'SV031', 7.4), ('MH005', 'SV031', 8.6),
('MH006', 'SV032', 7.9), ('MH007', 'SV032', 9.0), ('MH008', 'SV032', 7.1), ('MH009', 'SV032', 5.5), ('MH010', 'SV032', 6.7),
('MH011', 'SV033', 4.2), ('MH012', 'SV033', 7.4), ('MH013', 'SV033', 5.9), ('MH014', 'SV033', 6.9), ('MH015', 'SV033', 8.3),
('MH016', 'SV034', 7.7), ('MH017', 'SV034', 9.1), ('MH018', 'SV034', 7.5), ('MH019', 'SV034', 8.8), ('MH020', 'SV034', 6.6),
('MH021', 'SV035', 8.1), ('MH022', 'SV035', 7.3), ('MH023', 'SV035', 8.7), ('MH024', 'SV035', 9.0), ('MH025', 'SV035', 7.4),
('MH026', 'SV036', 8.6), ('MH027', 'SV036', 1.9), ('MH028', 'SV036', 9.0), ('MH029', 'SV036', 7.1), ('MH030', 'SV036', 8.5),
('MH001', 'SV037', 6.7), ('MH002', 'SV037', 8.2), ('MH003', 'SV037', 7.4), ('MH004', 'SV037', 8.9), ('MH005', 'SV037', 6.9),
('MH006', 'SV038', 3.3), ('MH007', 'SV038', 4.7), ('MH008', 'SV038', 9.1), ('MH009', 'SV038', 7.5), ('MH010', 'SV038', 8.8),
('MH011', 'SV039', 6.6), ('MH012', 'SV039', 5.1), ('MH013', 'SV039', 7.3), ('MH014', 'SV039', 5.7), ('MH015', 'SV039', 9.0),
('MH016', 'SV040', 7.4), ('MH017', 'SV040', 8.6), ('MH018', 'SV040', 7.9), ('MH019', 'SV040', 9.0), ('MH020', 'SV040', 7.1),

('MH021', 'SV041', 8.5), ('MH022', 'SV041', 6.7), ('MH023', 'SV041', 8.2), ('MH024', 'SV041', 7.4), ('MH025', 'SV041', 8.9),
('MH026', 'SV042', 6.9), ('MH027', 'SV042', 8.3), ('MH028', 'SV042', 7.7), ('MH029', 'SV042', 9.1), ('MH030', 'SV042', 3.5),
('MH001', 'SV043', 8.8), ('MH002', 'SV043', 6.6), ('MH003', 'SV043', 5.1), ('MH004', 'SV043', 7.3), ('MH005', 'SV043', 8.7),
('MH006', 'SV044', 4.0), ('MH007', 'SV044', 7.4), ('MH008', 'SV044', 8.6), ('MH009', 'SV044', 4.9), ('MH010', 'SV044', 9.0),
('MH011', 'SV045', 7.1), ('MH012', 'SV045', 8.5), ('MH013', 'SV045', 6.7), ('MH014', 'SV045', 8.2), ('MH015', 'SV045', 7.4),
('MH016', 'SV046', 8.9), ('MH017', 'SV046', 6.9), ('MH018', 'SV046', 2.3), ('MH019', 'SV046', 7.7), ('MH020', 'SV046', 9.1),
('MH021', 'SV047', 4.5), ('MH022', 'SV047', 8.8), ('MH023', 'SV047', 6.6), ('MH024', 'SV047', 8.1), ('MH025', 'SV047', 7.3),
('MH026', 'SV048', 8.7), ('MH027', 'SV048', 9.0), ('MH028', 'SV048', 7.4), ('MH029', 'SV048', 8.6), ('MH030', 'SV048', 7.9),
('MH001', 'SV049', 9.0), ('MH002', 'SV049', 7.1), ('MH003', 'SV049', 8.5), ('MH004', 'SV049', 6.7), ('MH005', 'SV049', 8.2),
('MH006', 'SV050', 7.4), ('MH007', 'SV050', 8.9), ('MH008', 'SV050', 2.9), ('MH009', 'SV050', 8.3), ('MH010', 'SV050', 7.7),

('MH011', 'SV051', 9.1), ('MH012', 'SV051', 7.5), ('MH013', 'SV051', 8.8), ('MH014', 'SV051', 6.6), ('MH015', 'SV051', 8.1),
('MH016', 'SV052', 7.3), ('MH017', 'SV052', 8.7), ('MH018', 'SV052', 9.0), ('MH019', 'SV052', 7.4), ('MH020', 'SV052', 8.6),
('MH021', 'SV053', 7.9), ('MH022', 'SV053', 9.0), ('MH023', 'SV053', 7.1), ('MH024', 'SV053', 8.5), ('MH025', 'SV053', 6.7),
('MH026', 'SV054', 8.2), ('MH027', 'SV054', 7.4), ('MH028', 'SV054', 8.9), ('MH029', 'SV054', 6.9), ('MH030', 'SV054', 8.3),
('MH001', 'SV055', 7.7), ('MH002', 'SV055', 1.1), ('MH003', 'SV055', 7.5), ('MH004', 'SV055', 0.8), ('MH005', 'SV055', 6.6),
('MH006', 'SV056', 8.1), ('MH007', 'SV056', 7.3), ('MH008', 'SV056', 8.7), ('MH009', 'SV056', 9.0), ('MH010', 'SV056', 7.4),
('MH011', 'SV057', 8.6), ('MH012', 'SV057', 7.9), ('MH013', 'SV057', 9.0), ('MH014', 'SV057', 7.1), ('MH015', 'SV057', 8.5),
('MH016', 'SV058', 6.7), ('MH017', 'SV058', 8.2), ('MH018', 'SV058', 7.4), ('MH019', 'SV058', 8.9), ('MH020', 'SV058', 6.9),
('MH021', 'SV059', 8.3), ('MH022', 'SV059', 7.7), ('MH023', 'SV059', 9.1), ('MH024', 'SV059', 7.5), ('MH025', 'SV059', 4.8),
('MH026', 'SV060', 3.6), ('MH027', 'SV060', 8.1), ('MH028', 'SV060', 7.3), ('MH029', 'SV060', 8.7), ('MH030', 'SV060', 9.0)
) AS v(SubjectID, StudentID, Score)
WHERE NOT EXISTS (
    SELECT 1 FROM dbo.Transcript t
    WHERE t.StudentID = v.StudentID
      AND t.SubjectID = v.SubjectID
);
GO

