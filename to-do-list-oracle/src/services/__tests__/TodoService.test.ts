/// <reference types="jest" />

import '@jest/globals';
import { TodoService } from '../TodoService';
import { createConnection } from '../../database/connection';

jest.mock('../../database/connection');

describe('TodoService', () => {
  let todoService: TodoService;
  
  beforeEach(() => {
    todoService = new TodoService();
  });

  describe('create', () => {
    it('new todo should be created', async () => {
      const mockExecute = jest.fn().mockResolvedValue({
        outBinds: {
          id: [1],
          title_out: ['Test Todo'],
          completed: [0],
          created_at: [new Date()]
        }
      });

      const mockConnection = {
        execute: mockExecute,
        close: jest.fn()
      };

      (createConnection as jest.Mock).mockResolvedValue(mockConnection);

      const result = await todoService.create('Test Todo');

      expect(result).toHaveProperty('id', 1);
      expect(result).toHaveProperty('title', 'Test Todo');
      expect(result).toHaveProperty('completed', false);
      expect(result).toHaveProperty('created_at');
      expect(mockConnection.close).toHaveBeenCalled();
    });
  });

  describe('getAll', () => {
    it('should return all todos', async () => {
      const mockExecute = jest.fn().mockResolvedValue({
        rows: [{ id: 1, title: 'Test Todo', completed: false, created_at: new Date() }]
      });

      const mockConnection = {
        execute: mockExecute,
        close: jest.fn()
      };

      (createConnection as jest.Mock).mockResolvedValue(mockConnection);

      const result = await todoService.getAll();
      expect(result).toEqual([{ id: 1, title: 'Test Todo', completed: false, created_at: new Date() }]);
      expect(mockConnection.close).toHaveBeenCalled();
    });
  });
}); 
