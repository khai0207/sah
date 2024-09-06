--v3
--[[
Buluoli_2
1
0
penxue
3
{default,gongji7,1,0,0,38,1.000000}{default,gongji5,1,0,39,106,1.000000}{default,qianjin,1,0,107,108,1.000000}
2
{default,0.000000,0.000000,0.000000,0.000000,0,0,0,1,0.000000,0,0,0,0}{default,0.000000,0.000000,360.000000,250.000000,0,2,2,3,0.000000,0,0,0,0}
0

0

0

daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"gongji7",1.000000,0,38,1},{"gongji5",1.000000,39,106,1},{"qianjin",1.000000,107,108,1}}, 
pos_sequence      = {{0.000000,0.000000,0.000000,0.000000,0,0,0,1,0.000000,0,0,0,0},{0.000000,0.000000,360.000000,250.000000,0,2,2,3,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {}, 
bloodNum          = 8,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
