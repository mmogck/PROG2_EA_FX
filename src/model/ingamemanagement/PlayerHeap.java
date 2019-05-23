package model.ingamemanagement;

import model.figure.Figure;

public class PlayerHeap
{
    private int heapSize;
    private Figure heapBuffer[];
    private int pos;

    public PlayerHeap(int size)
    {
        this.heapSize = size;
        this.pos = 0;
        this.heapBuffer = new Figure[size];
    }

    private void heapswap(int i, int j)
    {
        Figure t;
        t = this.heapBuffer[i];
        this.heapBuffer[i] = this.heapBuffer[j];
        this.heapBuffer[j] = t;
    }

    //heapify() -> MAX Heap Eigenschaft wiederherstellen
    private void heapify()
    {
        int currentpos = this.pos - 1;

        while (currentpos != 0)
        {
            int parentIndex = (currentpos - 1) / 2;
            int parentValue = this.heapBuffer[parentIndex].getMovementPoints(); //Ausstauschen mit Inititative
            int currentValue = this.heapBuffer[currentpos].getMovementPoints();

            if (currentValue > parentValue)
            {
                heapswap(parentIndex, currentpos);
            } else
            {
                break;
            }

            currentpos = parentIndex;
        }
    }

    //Nach dem ein Element entnommen wurde, von oben ab das Element an die Richtige Position bringen.
    private void fix()
    {
        int k = 0;
        while (k < this.pos / 2) //Solange jeder Knoten im Heap mindestens ein Kindknoten hat
        {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int swappos = k, swapValue;

            int currentValue = this.heapBuffer[k].getMovementPoints();

            if ((left < this.pos - 1)
                && this.heapBuffer[left].getMovementPoints() > currentValue)
            {
                swappos = left;
            }

            swapValue = this.heapBuffer[swappos].getMovementPoints();

            if ((right < this.pos)
                && this.heapBuffer[right].getMovementPoints() > swapValue)
            {
                swappos = right;
            }

            if (swappos != k)
            {
                heapswap(swappos, k);
            } else
            {
                break;
            }

            k = swappos;
        }
    }

    public int add(Figure f)
    {
        if (this.pos == this.heapSize)
        {
            return 0;
        }
        this.heapBuffer[this.pos] = f;
        this.pos++;

        heapify();

        return 1;
    }

    public Figure get()
    {
        Figure ret;
        ret = this.heapBuffer[0];

        heapswap(0, this.pos - 1);

        this.pos -= 1;
        fix();

        return ret;
    }

    @Override
    public String toString()
    {
        return "PlayerHeap{" + "heapBuffer=" + heapBuffer + '}';
    }
}
