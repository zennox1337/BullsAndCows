# BullsAndCows
Написать игру "Быки-Коровы"

1) Правила игры. Программа задумывает строку из четырех разных цифр. Пример: 0123
2) Человек пытается угадать это число путем ввода в консоль строки из четырех цифр. Пример: 3521
3) Программа выдает следующий результат. Если цифра есть в угадываемой строке и стоит на своем месте, то это "бык", если цифра есть в угадываемой строке, но не на своем месте, то это "корова". Пример вывода программы: 2 коровы 1 бык.
(Окончания слов должны быть корректными, то есть не должно быть 1 быка 2 корова)
4) Результат игры должен записываться в файл в следующем формате
	Game №1 05.08.2021 6:46 Загаданная строка 0123
		Запрос: 3521 Ответ: 2 коровы 1 бык
		Запрос: 0134 Ответ: 1 корова 2 быка
		...
		Строка была угадана за 5 попыток.
	Game №2 05.08.2021 7:52 Загаданная строка 0452
		...
5) При запуске программы заново: из файла необходимо прочитать номер последней игры и при записи продолжить нумерацию.
6*) Человек угадывает строку, а программа угадывает
PS. Постараться написать в стиле ООП(SOLID)
