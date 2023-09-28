#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void diceRoll(int *dice, int times);

int main()
{
	int dice[4];
	int sum = 0;
	srand(time(NULL));
	diceRoll(dice, 4);
	printf("Dice Roll! I am rolling four dice and dropping the lowest value\n");
	for(int i =0; i < 4; i++)
	{	
		if (dice[i] != 0)
		{
			printf("%d!\n", dice[i]);
		}	sum = sum + dice[i];
	}

    printf("The sum of the dice roll without the lowest value is %d\n", sum);
	return 0;
}

void diceRoll(int *dice, int times)
{
	int sides, lower;
	sides = 6;
	lower = 1;

	for(int i = 0; i < times; i++)
	{
		dice[i] = (rand() % (sides-lower + 1)) + lower;
	}

	int lowestI = 0;

	for(int i = 1; i < times; i++)
	{
		if (dice[i] < dice[lowestI])
		{
			lowestI =  i;
		}
	}
	dice[lowestI] = 0;
}
