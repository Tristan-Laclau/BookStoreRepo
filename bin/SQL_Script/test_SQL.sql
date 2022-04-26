SELECT *
FROM t_books;

SELECT t_books.* , t_themes.*
FROM t_books JOIN book_details ON t_books.IdBook = book_details.IdBook
			JOIN t_themes ON book_details.IdTheme = t_themes.IdTheme;
			