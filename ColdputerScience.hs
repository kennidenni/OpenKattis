import Data.List.Split
import Data.Char

main = do
    random <- getLine
    tallene <- getLine
    let xs = splitOn " " tallene
    print xs
    let tall = getNumber xs 0
    print tall 

getNumber :: [String] -> Int -> Int 
getNumber [] tall = tall
getNumber (x:xs) tall 
    | (head x) == '-' = getNumber xs (tall+1)
    | isDigit (head x) = getNumber xs tall
    