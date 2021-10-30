:- use_module(library(clpfd)).

% Entradas: Una matriz de números
% Salidas: La matriz de números solucionada
% Restricciones: La matriz debe contener números del 1 al 9 o campos vacios.
% Objetivo: Completar el tablero de sudoku.
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

% Entradas: Filas de la matriz
% Salidas: Restricción de los cuadrados de sudoku
% Restricciones: Deben ser filas de números del 1 al 9 o vacías
% Objetivo: Definar las restricciones de los cuadrados del sudokuX
blocks([], [], []).
blocks([N1,N2,N3|Ns1], [N4,N5,N6|Ns2], [N7,N8,N9|Ns3]) :-
        all_distinct([N1,N2,N3,N4,N5,N6,N7,N8,N9]),
        blocks(Ns1, Ns2, Ns3).

% Entradas: Una matriz, una lista y una variable
% Salidas: Extrae los elementos de la diagonal del tablero
% Restricciones: Deben ser elementos del tablero del sudoku
% Objetivo: Extraer las diagonales.
extract_element(L, L1, [H|L1]):- 
                length(L1, N1), 
                length(L2, N1), 
                append(L2, [H|_], L).

% Entradas: La matriz y una variable para guardar la diagonal
% Salidas: la diagonal
% Restricciones: Debe ser una matriz de números enteros
% Objetivo: Obtener las 2 diagonales de la matriz
diagonal(Matrix,Out):-
        diagonal1(Matrix,Diagonal1),
        diagonal2(Matrix,Diagonal2),
        append([Diagonal1],[Diagonal2],Out).

% Entradas: la matriz y una variable para guardar
% Salidas: la diagonal
% Restricciones: debe ser una matriz de números enteros
% Objetivo: Obtener la diagonal.
diagonal1(In, Out):- 
                foldl(extract_element, In, [], Res), 
                reverse(Res,Out).

% Entradas: La matriz y una variable para guardar
% Salidas: la diagonal inversa
% Restricciones: deben ser una matriz de números enteros
% Objetivo: Obtener la diagonal inversa
diagonal2(In, Out):- 
                reverse(In, In2),
                foldl(extract_element, In2, [], Out).

% Entradas: Una matriz y una variable para el resultado
% Salidas: El tablero solcionado
% Restricciones: Deben ser una matriz de números enteros o campos vacíos
% Objetivo:Obtener la solución del sudoku ó generar uno.
getBoard(Rows,Out):-
        sudoku(Rows), 
        maplist(label, Rows),
        Out=Rows.
			