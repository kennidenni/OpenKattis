main = do
    input <- getLine
    program (read input)

program tall = do
    if (tall == 0) then do
        return()
    else do
        input <- getLine
        if (even (read input)) then do
            putStrLn (input ++ " is even") >> program (tall-1)
        else do
            putStrLn (input ++ " is odd") >> program (tall-1)
