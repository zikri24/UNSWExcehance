USE [master]
GO
/****** Object:  Database [UNSWExchange]    Script Date: 6/09/2015 10:15:38 PM ******/
CREATE DATABASE [UNSWExchange]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'UNSWExchange', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\UNSWExchange.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'UNSWExchange_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\UNSWExchange_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [UNSWExchange] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [UNSWExchange].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [UNSWExchange] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [UNSWExchange] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [UNSWExchange] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [UNSWExchange] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [UNSWExchange] SET ARITHABORT OFF 
GO
ALTER DATABASE [UNSWExchange] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [UNSWExchange] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [UNSWExchange] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [UNSWExchange] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [UNSWExchange] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [UNSWExchange] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [UNSWExchange] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [UNSWExchange] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [UNSWExchange] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [UNSWExchange] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [UNSWExchange] SET  DISABLE_BROKER 
GO
ALTER DATABASE [UNSWExchange] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [UNSWExchange] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [UNSWExchange] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [UNSWExchange] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [UNSWExchange] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [UNSWExchange] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [UNSWExchange] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [UNSWExchange] SET RECOVERY FULL 
GO
ALTER DATABASE [UNSWExchange] SET  MULTI_USER 
GO
ALTER DATABASE [UNSWExchange] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [UNSWExchange] SET DB_CHAINING OFF 
GO
ALTER DATABASE [UNSWExchange] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [UNSWExchange] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'UNSWExchange', N'ON'
GO
USE [UNSWExchange]
GO
/****** Object:  StoredProcedure [dbo].[sp_accounts_insert]    Script Date: 6/09/2015 10:15:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_accounts_insert]
			
			(@password  VARCHAR(50)
           ,@role VARCHAR(50)
           ,@firstName VARCHAR(50)
           ,@lastName VARCHAR(50)
           ,@studentId VARCHAR(50)
           ,@sex VARCHAR(50)
           ,@email VARCHAR(50)
           ,@street VARCHAR(50)
           ,@city VARCHAR(50)
           ,@state VARCHAR(50)
           ,@postcode VARCHAR(50)
           ,@DOB date
           ,@phone VARCHAR(50)
		   ,@accountId int OUTPUT)
AS
BEGIN	

INSERT INTO [dbo].[Accounts]
           ([Password]
           ,[Role]
           ,[FirstName]
           ,[LastName]
           ,[StudentId]
           ,[Sex]
           ,[Email]
           ,[Street]
           ,[City]
           ,[State]
           ,[Postcode]
           ,[DOB]
           ,[Phone])
     VALUES
			(@password
           ,@role
           ,@firstName
           ,@lastName
           ,@studentId
           ,@sex
           ,@email
           ,@street
           ,@city
           ,@state
           ,@postcode
           ,@DOB
           ,@phone)

		   SET @accountId = @@IDENTITY
END



GO
/****** Object:  StoredProcedure [dbo].[sp_accounts_login]    Script Date: 6/09/2015 10:15:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_accounts_login] 
	(
	@username varchar(50),
	@password varchar(50),
	@result bit OUTPUT,
	@accountId int OUTPUT,
	@role varchar(50) OUTPUT,
	@name varchar(50) OUTPUT
	)
AS
BEGIN
	if 	(SELECT  Password  FROM  Accounts WHERE Email = @username ) = @password
	BEGIN
		SET @result = 1	
		SET @accountId = (SELECT  AccountId  FROM  Accounts WHERE Email = @username )
		SET @role = (SELECT  Role  FROM  Accounts WHERE Email = @username )
		SET @name = (SELECT  LastName  FROM  Accounts WHERE Email = @username )
		
	END
ELSE
	BEGIN
		SET @result = 0	
	END
	PRINT @result
END

GO
/****** Object:  StoredProcedure [dbo].[sp_applications_insert]    Script Date: 6/09/2015 10:15:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_applications_insert] 
	(
	@universityName varchar(50),
	@country varchar(50),
	@startDate date,
	@finishDate date,
	@unswPartner bit,
	@transferCredit bit,
	@accountId int,
	@applicationId int OUTPUT
	)

AS
BEGIN
	 INSERT INTO [dbo].[Applications] ( [UniversityName], [Country],[StartDate],[EndDate],[UNSWPartner],  [TransferCridetToUNSW], [AccountId]  )
	  VALUES						(@universityName, @country, @startDate, @finishDate, @unswPartner, @transferCredit, @accountId);
	  SET @applicationId = @@IDENTITY
END


GO
/****** Object:  StoredProcedure [dbo].[sp_applications_insert_education]    Script Date: 6/09/2015 10:15:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_applications_insert_education]
	(@applicationId int,
	@degreeName varchar(50),
	@programCode varchar(50),
	@faculty varchar(50),
	@yearOfStudy int,
	@yearToComplete int,
	@wam decimal,
	@universityName varchar(50),
	@country varchar(50),
	@startDate date,
	@finishDate date,
	@unswPartner bit,
	@transferCredit bit)
AS
BEGIN
	UPDATE [dbo].[Applications]
	 SET [DegreeName] = @degreeName,
		[ProgramCode] = @programCode,
		[Faculty] = @faculty,
		[YearOfStudy] = @yearOfStudy,
		[YearToComplete] = @yearToComplete,
		[WAM] = @wam,
		[UniversityName] = @universityName,
		[Country]  = @country,
		[StartDate] = @startDate,
		[FinishDate] = @finishDate,
		[UNSWPartner] = @unswPartner, 
		[TransferCreditToUNSW] = @transferCredit

	 WHERE [ApplicationId] = @applicationId
END


GO
/****** Object:  StoredProcedure [dbo].[sp_educations_insert]    Script Date: 6/09/2015 10:15:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[sp_educations_insert]
	@accountId int,
	@degreeName varchar(50),
	@programCode varchar(50),
	@faculty varchar(50),
	@yearOfStudy varchar(50),
	@yearToComplete int,
	@wam decimal
AS
BEGIN

INSERT INTO [dbo].[Educations]
           ([AccountId]
           ,[DegreeName]
           ,[ProgramCode]
           ,[Faculty]
           ,[YearOfStudy]
           ,[YearToComple]
           ,[WAM])
     VALUES
           (@accountId
           ,@degreeName
           ,@programCode
           ,@faculty
           ,@yearOfStudy
           ,@yearToComplete
           ,@wam)

		   
END

GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 6/09/2015 10:15:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Accounts](
	[AccountId] [int] IDENTITY(1,1) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[Role] [varchar](50) NULL,
	[FirstName] [varchar](50) NULL,
	[LastName] [varchar](50) NULL,
	[StudentId] [varchar](50) NOT NULL,
	[Sex] [varchar](50) NULL,
	[Email] [varchar](50) NULL,
	[Street] [varchar](50) NULL,
	[City] [varchar](50) NULL,
	[State] [varchar](50) NULL,
	[Postcode] [varchar](50) NULL,
	[DOB] [date] NULL,
	[Phone] [varchar](50) NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[AccountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Applications]    Script Date: 6/09/2015 10:15:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Applications](
	[ApplicationNo] [int] IDENTITY(1,1) NOT NULL,
	[UniversityName] [varchar](50) NULL,
	[Country] [varchar](50) NULL,
	[StartDate] [date] NULL,
	[EndDate] [date] NULL,
	[UNSWPartner] [bit] NULL CONSTRAINT [DF_Application_UNSWPartner]  DEFAULT ((0)),
	[TransferCridetToUNSW] [bit] NULL CONSTRAINT [DF_Application_TransferCridetToUNSW]  DEFAULT ((0)),
	[SupportQuestion] [text] NULL,
	[DemonstrationQuestion] [text] NULL,
	[BringBackQuestion] [text] NULL,
	[Status] [varchar](50) NULL CONSTRAINT [DF_Application_Status]  DEFAULT ('Pending'),
	[AccountId] [int] NULL,
 CONSTRAINT [PK_Application] PRIMARY KEY CLUSTERED 
(
	[ApplicationNo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Educations]    Script Date: 6/09/2015 10:15:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Educations](
	[AccountId] [int] NOT NULL,
	[DegreeName] [varchar](50) NULL,
	[ProgramCode] [varchar](50) NULL,
	[Faculty] [varchar](50) NULL,
	[YearOfStudy] [varchar](50) NULL,
	[YearToComple] [int] NULL,
	[WAM] [decimal](4, 2) NULL,
 CONSTRAINT [PK_Educations] PRIMARY KEY CLUSTERED 
(
	[AccountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Accounts] ON 

INSERT [dbo].[Accounts] ([AccountId], [Password], [Role], [FirstName], [LastName], [StudentId], [Sex], [Email], [Street], [City], [State], [Postcode], [DOB], [Phone]) VALUES (1, N'30919823', N'Student', NULL, N'Zikri', N'z5021198', N'Male', N'zikri24@gmail.com', N'Anzac Parade', N'Maroubra', N'NSW', N'2035', CAST(N'1982-09-30' AS Date), N'0406464857')
INSERT [dbo].[Accounts] ([AccountId], [Password], [Role], [FirstName], [LastName], [StudentId], [Sex], [Email], [Street], [City], [State], [Postcode], [DOB], [Phone]) VALUES (2, N'12345', N'Student', N'Omar', N'Zikri', N'z5021198', N'Male', N'zikri@zikri', N'Anzac Parade', N'Maroubra', N'NSW', N'2035', CAST(N'2015-09-02' AS Date), N'04046464857')
INSERT [dbo].[Accounts] ([AccountId], [Password], [Role], [FirstName], [LastName], [StudentId], [Sex], [Email], [Street], [City], [State], [Postcode], [DOB], [Phone]) VALUES (6, N'12345', N'Student', N'Silvia', N'Mereu', N'z5215284', N'Male', N'silvia@silvia', N'Anzac Parade', N'Maroubra', N'NSW', N'2035', CAST(N'1985-10-01' AS Date), N'0412518')
INSERT [dbo].[Accounts] ([AccountId], [Password], [Role], [FirstName], [LastName], [StudentId], [Sex], [Email], [Street], [City], [State], [Postcode], [DOB], [Phone]) VALUES (7, N'12345', N'Student', N'Silvia', N'Mereu', N'ijij', N'Male', N'ijij', N'iji', N'jij', N'NSW', N'jiji', CAST(N'2015-09-01' AS Date), N'ijij')
SET IDENTITY_INSERT [dbo].[Accounts] OFF
SET IDENTITY_INSERT [dbo].[Applications] ON 

INSERT [dbo].[Applications] ([ApplicationNo], [UniversityName], [Country], [StartDate], [EndDate], [UNSWPartner], [TransferCridetToUNSW], [SupportQuestion], [DemonstrationQuestion], [BringBackQuestion], [Status], [AccountId]) VALUES (1000, N'Columbia', N'USA', CAST(N'2015-08-30' AS Date), CAST(N'2016-01-30' AS Date), 0, 0, N'Some answers Some answers Some answersSome answers Some answers Some answers  ', N'Some answers Some answers Some answers Some answers ', N'Some answers Some answers Some answers Some answers ', N'Pending', NULL)
INSERT [dbo].[Applications] ([ApplicationNo], [UniversityName], [Country], [StartDate], [EndDate], [UNSWPartner], [TransferCridetToUNSW], [SupportQuestion], [DemonstrationQuestion], [BringBackQuestion], [Status], [AccountId]) VALUES (1004, N'Sydnet', N'Australia', CAST(N'2015-09-04' AS Date), CAST(N'2015-10-02' AS Date), 1, 1, NULL, NULL, NULL, N'Pending', 1)
SET IDENTITY_INSERT [dbo].[Applications] OFF
INSERT [dbo].[Educations] ([AccountId], [DegreeName], [ProgramCode], [Faculty], [YearOfStudy], [YearToComple], [WAM]) VALUES (7, N'Information Systems', N'3739', N'Business', N'2nd Year', 2016, CAST(70.00 AS Decimal(4, 2)))
ALTER TABLE [dbo].[Applications]  WITH CHECK ADD  CONSTRAINT [FK_Applications_Accounts] FOREIGN KEY([AccountId])
REFERENCES [dbo].[Accounts] ([AccountId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Applications] CHECK CONSTRAINT [FK_Applications_Accounts]
GO
ALTER TABLE [dbo].[Educations]  WITH CHECK ADD  CONSTRAINT [FK_Educations_Accounts] FOREIGN KEY([AccountId])
REFERENCES [dbo].[Accounts] ([AccountId])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Educations] CHECK CONSTRAINT [FK_Educations_Accounts]
GO
/****** Object:  DdlTrigger [rds_deny_backups_trigger]    Script Date: 6/09/2015 10:15:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [rds_deny_backups_trigger] ON DATABASE WITH EXECUTE AS 'dbo' FOR
 ADD_ROLE_MEMBER, GRANT_DATABASE AS BEGIN
   SET NOCOUNT ON;
   SET ANSI_PADDING ON;
 
   DECLARE @data xml;
   DECLARE @user sysname;
   DECLARE @role sysname;
   DECLARE @type sysname;
   DECLARE @sql NVARCHAR(MAX);
   DECLARE @permissions TABLE(name sysname PRIMARY KEY);
   
   SELECT @data = EVENTDATA();
   SELECT @type = @data.value('(/EVENT_INSTANCE/EventType)[1]', 'sysname');
    
   IF @type = 'ADD_ROLE_MEMBER' BEGIN
      SELECT @user = @data.value('(/EVENT_INSTANCE/ObjectName)[1]', 'sysname'),
       @role = @data.value('(/EVENT_INSTANCE/RoleName)[1]', 'sysname');

      IF @role IN ('db_owner', 'db_backupoperator') BEGIN
         SELECT @sql = 'DENY BACKUP DATABASE, BACKUP LOG TO ' + QUOTENAME(@user);
         EXEC(@sql);
      END
   END ELSE IF @type = 'GRANT_DATABASE' BEGIN
      INSERT INTO @permissions(name)
      SELECT Permission.value('(text())[1]', 'sysname') FROM
       @data.nodes('/EVENT_INSTANCE/Permissions/Permission')
      AS DatabasePermissions(Permission);
      
      IF EXISTS (SELECT * FROM @permissions WHERE name IN ('BACKUP DATABASE',
       'BACKUP LOG'))
         RAISERROR('Cannot grant backup database or backup log', 15, 1) WITH LOG;       
   END
END



GO
SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER OFF
GO
ENABLE TRIGGER [rds_deny_backups_trigger] ON DATABASE
GO
USE [master]
GO
ALTER DATABASE [UNSWExchange] SET  READ_WRITE 
GO
