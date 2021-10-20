createBoard(Board):- 
	length(Zs, 81),
	maplist(random(1,10), Zs),
	Board = Zs.