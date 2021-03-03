import Data.List.Split
main = do
	input <- getLine
	let ys = splitOn " " input
	putStrLn (head $ tail ys)