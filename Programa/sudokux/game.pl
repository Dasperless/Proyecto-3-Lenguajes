:- use_module(library(clpfd)).
sudoku(Rows) :-
        % Crea una matriz vacía 
        length(Rows, 9), maplist(same_length(Rows), Rows),
        append(Rows, Vs), Vs ins 1..9,
        maplist(all_distinct, Rows), %Todas las filas deben ser distintas
        transpose(Rows, Columns),%Declara las columnas
        maplist(all_distinct, Columns),%Todas las columnas deben ser distintas
        diagonal(Rows,Diagonals),
        maplist(all_distinct,Diagonals),
        Rows = [As,Bs,Cs,Ds,Es,Fs,Gs,Hs,Is],
        blocks(As, Bs, Cs),
        blocks(Ds, Es, Fs),
        blocks(Gs, Hs, Is).

blocks([], [], []).
blocks([N1,N2,N3|Ns1], [N4,N5,N6|Ns2], [N7,N8,N9|Ns3]) :-
        all_distinct([N1,N2,N3,N4,N5,N6,N7,N8,N9]),
        blocks(Ns1, Ns2, Ns3).

extract_element(L, L1, [H|L1]):- 
                length(L1, N1), 
                length(L2, N1), 
                append(L2, [H|_], L).

diagonal(Matrix,Out):-
        diagonal1(Matrix,Diagonal1),
        diagonal2(Matrix,Diagonal2),
        append([Diagonal1],[Diagonal2],Out).

diagonal1(In, Out):- 
                foldl(extract_element, In, [], Res), 
                reverse(Res,Out).

diagonal2(In, Out):- 
                reverse(In, In2),
                foldl(extract_element, In2, [], Out).

problem(1, [[_,_,_,_,_,_,_,_,_],
            [_,_,_,_,_,_,_,_,_],
            [_,_,_,_,_,_,_,_,_],
            [_,_,_,_,_,_,_,_,_],
            [_,_,1,_,_,_,_,_,_],
            [_,_,_,_,_,_,2,_,_],
            [_,_,_,_,_,_,_,_,_],
            [_,_,_,_,_,_,_,_,_],
            [_,_,_,_,_,_,_,_,_]]).
			